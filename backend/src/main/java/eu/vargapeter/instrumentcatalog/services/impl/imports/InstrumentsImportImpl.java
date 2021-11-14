package eu.vargapeter.instrumentcatalog.services.impl.imports;

import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.exceptions.InvalidExcelCellException;
import eu.vargapeter.instrumentcatalog.model.Instrument;
import eu.vargapeter.instrumentcatalog.services.InstrumentsImport;
import eu.vargapeter.instrumentcatalog.services.impl.ImportFromFileImpl;
import eu.vargapeter.instrumentcatalog.services.InstrumentService;
import eu.vargapeter.instrumentcatalog.util.Constans;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class InstrumentsImportImpl implements InstrumentsImport {

    private InstrumentService instrumentService;

    @Autowired
    public InstrumentsImportImpl(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @Override
    public void instrumentReadFromExcel(Row currentRow) {

        ImportFromFileImpl.DB_ROW++;

        Instrument instrument = new Instrument();


        try {
            // catalog number
            instrument.setCatalogNumber(currentRow.getCell(Constans.IMPORT_INSTRUMENT_CATALOG_NUMBER_CELL)
                    .getStringCellValue());

            // brand
            instrument.setBrand(currentRow.getCell(Constans.IMPORT_INSTRUMENT_BRAND_CELL)
                    .getStringCellValue());

            // model
            if (currentRow.getCell(Constans.IMPORT_INSTRUMENT_MODEL_CELL).getStringCellValue().isBlank() ||
                    currentRow.getCell(Constans.IMPORT_INSTRUMENT_MODEL_CELL).getStringCellValue().isEmpty()) {
                throw new InvalidExcelCellException(Translator.toLocale("import.instrument.modelerror"));
            } else {
                instrument.setModel(currentRow.getCell(Constans.IMPORT_INSTRUMENT_MODEL_CELL).getStringCellValue());
            }

            // type
            instrument.setInstrumentType(currentRow.getCell(Constans.IMPORT_INSTRUMENT_TYPE_CELL)
                    .getStringCellValue());

            // serial number
            instrument.setSerialNumber(currentRow.getCell(Constans.IMPORT_INSTRUMENT_SERIAL_NUM_CELL)
                    .getStringCellValue());

            // location
            instrument.setLocation(currentRow.getCell(Constans.IMPORT_INSTRUMENT_PLACE_CELL)
                    .getStringCellValue());

            // comment
            instrument.setComment(currentRow.getCell(Constans.IMPORT_INSTRUMENT_COMMENT_CELL)
                    .getStringCellValue());

            // high value
            if (currentRow.getCell(Constans.IMPORT_INSTRUMENT_HIGH_VALUE_CELL)
                    .getStringCellValue().equals(Constans.IMPORT_INSTRUMENT_YES_OR_NO_CELL)) {
                instrument.setHighValue(true);
            } else {
                instrument.setHighValue(false);
            }

            // waste
            if (currentRow.getCell(Constans.IMPORT_INSTRUMENT_WASTE_CELL).getStringCellValue()
                    .equals(Constans.IMPORT_INSTRUMENT_YES_OR_NO_CELL)) {
                instrument.setWaste(true);
            } else {
                instrument.setWaste(false);
            }


        } catch (Exception e) {
            throw new InvalidExcelCellException(Translator.toLocale("import.instrument.invalidcell")
                    + e.getMessage() + Translator.toLocale("import.instrument.invalidrow") + currentRow.getRowNum());
        }

        // save instrument
        saveInstrument(instrument);

    }

    private void saveInstrument(Instrument instrument) {
        instrumentService.saveInstrument(instrument);
        ImportFromFileImpl.DB_NEW_ROW++;
    }
}
