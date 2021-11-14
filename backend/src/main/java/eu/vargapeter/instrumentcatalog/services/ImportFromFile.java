package eu.vargapeter.instrumentcatalog.services;

import java.io.FileNotFoundException;

public interface ImportFromFile {

    public void importFromExcel(String fileName, String importType) throws Exception;
}
