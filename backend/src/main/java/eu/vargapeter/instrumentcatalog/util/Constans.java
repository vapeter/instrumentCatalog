package eu.vargapeter.instrumentcatalog.util;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Constans {

    public final static List<Locale> LOCALES = Arrays.asList(
            new Locale("en"),
            new Locale("hu"));

    public final static String SQL_DATE_PATTERN = "yyyy-MM-dd";
    public final static String JSON_DATE_PATTERN = "yyyy-MM-dd";
    public final static DateTimeFormatter STUDENT_IMPORT_FROM_FILE_DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy. MM. dd.");

    //file url
    public static final String IMPORT_FILE_URL = "/home/";

    // Teacher excel cells
    public final static Integer IMPORT_TECHER_EDUCATIONAL_ID_CELL = 0;
    public final static Integer IMPORT_TECHER_NAME_PREFIX = 1;
    public final static Integer IMPORT_TECHER_FIRST_NAME = 2;
    public final static Integer IMPORT_TECHER_LAST_NAME = 3;

    // Student excel cells
    public final static Integer IMPORT_STUDENT_EDUCATIONAL_ID_CELL = 0;
    public final static Integer IMPORT_STUDENT_NAME_PREFIX_CELL = 1;
    public final static Integer IMPORT_STUDENT_FIRST_NAME_CELL = 2;
    public final static Integer IMPORT_STUDENT_LAST_NAME_CELL = 3;
    public final static Integer IMPORT_STUDENT_BIRTH_NAME_PREFIX_CELL = 5;
    public final static Integer IMPORT_STUDENT_BIRTH_FIRST_NAME_CELL = 6;
    public final static Integer IMPORT_STUDENT_BIRTH_LAST_NAME_CELL = 7;
    public final static Integer IMPORT_STUDENT_MOTHERS_NAME_PREFIX_CELL = 9;
    public final static Integer IMPORT_STUDENT_MOTHERS_FIRST_NAME_CELL = 10;
    public final static Integer IMPORT_STUDENT_MOTHERS_LAST_NAME_CELL = 11;
    public final static Integer IMPORT_STUDENT_BIRTH_DATE_CELL = 13;
    public final static Integer IMPORT_STUDENT_BIRTH_PLACE_CELL = 14;
    public final static Integer IMPORT_STUDENT_GENDER_CELL = 18;
    public final static String IMPORT_STUDENT_GENDER_WOMEN_CELL_DATA = "Nő";

    // instrument excel cells
    public static final Integer IMPORT_INSTRUMENT_CATALOG_NUMBER_CELL = 0;
    public static final Integer IMPORT_INSTRUMENT_BRAND_CELL = 1;
    public static final Integer IMPORT_INSTRUMENT_MODEL_CELL = 2;
    public static final Integer IMPORT_INSTRUMENT_TYPE_CELL = 3;
    public static final Integer IMPORT_INSTRUMENT_SERIAL_NUM_CELL = 4;
    public static final Integer IMPORT_INSTRUMENT_PLACE_CELL = 5;
    public static final Integer IMPORT_INSTRUMENT_COMMENT_CELL = 6;
    public static final Integer IMPORT_INSTRUMENT_HIGH_VALUE_CELL = 7;
    public static final Integer IMPORT_INSTRUMENT_WASTE_CELL = 8;
    public static final String IMPORT_INSTRUMENT_YES_OR_NO_CELL = "Igen";

    // excel check types
    public static final String STUDENT_IMPORT = "student";
    public static final String TEACHER_IMPORT = "teacher";
    public static final String INSTRUMENT_IMPORT = "instrument";

    public static final List<String> STUDENT_EXCEL_COLUMNS = List.of(
        "Oktazon", "Viselt név előtag", "Viselt név vezetéknév", "Viselt név keresztnév", "Viselt név névsorrend",
        "Születési név előtag", "Születési név vezetéknév", "Születési név keresztnév", "Születési név névsorrend",
        "Anyja neve előtag", "Anyja neve vezetéknév", "Anyja neve keresztnév", "Anyja neve névsorrend",
        "Születési dátum", "Születési hely", "Születési ország", "1. állampolgárság", "2. állampolgárság",
        "Nem", "TAJ szám", "TAJ ellenőrzés", "Állandó lakcím irányítószám", "Állandó lakcím település",
        "Állandó lakcím közterület név", "Állandó lakcím közterület jelleg", "Állandó lakcím házszám",
        "Állandó lakcím pontosítás", "Tartózkodási cím irányítószám", "Tartózkodási cím település",
        "Tartózkodási cím közterület név", "Tartózkodási cím közterület jelleg", "Tartózkodási cím házszám",
        "Tartózkodási cím pontosítás", "Adatkezelő intézmény OM azonosítója", "Adatkezelő intézmény neve",
        "Adatkezelő intézmény címe", "Tankötelezettség vége", "Tankötelezettséget teljesítő",
        "Sajátos nevelési igényű", "Beilleszkedéssel, tanulási, magatartási nehézséggel küzd",
        "Érvényes diákigazolvány száma", "Közoktatási intézmény neve", "Közoktatási intézmény székhelye",
        "OM azonosító", "Ügyviteli hely", "Jogviszony státusza", "Jogviszony kezdete", "Jogviszony (várható) befejezése",
        "Jogviszony jellege", "Vendégtanuló", "Egyéni munkarend", "Ideiglenes óvodai/ideiglenes vendégtanulói jogviszony",
        "Osztály", "Nyitott szolgáltatások", "Lezárt szolgáltatások",
        "A Belügyminisztérium személyiadat- és lakcímnyilvántartásában beazonosított",
        "Utolsó személyiadat- és lakcímnyilvántartás frissítés időpontja"
    );

    public static final List<String> INSTRUMENTS_EXCEL_COLUMNS = List.of(
        "Leltári szám", "Márka", "Model", "Típus", "Gyártási szám", "Hely", "Megjegyzés", "Nagyértékű", "Selejt");

    public static final List<String> TEACHER_EXCEL_COLUMNS = List.of(
        "Oktazon", "Viselt név előtag", "Viselt név vezetéknév", "Viselt név keresztnév", "Viselt név névsorrend",
        "Születési név előtag", "Születési név vezetéknév", "Születési név keresztnév", "Születési név névsorrend",
        "Anyja neve előtag", "Anyja neve vezetéknév", "Anyja neve keresztnév", "Anyja neve névsorrend",
        "Születési dátum", "Születési hely", "Születési ország", "1. állampolgárság", "2. állampolgárság",
        "Nem", "Végzettség szintje", "Állandó lakcím irányítószám", "Állandó lakcím település",
        "Állandó lakcím közterület név", "Állandó lakcím közterület jelleg", "Állandó lakcím házszám",
        "Állandó lakcím pontosítás", "Tartózkodási cím irányítószám", "Tartózkodási cím település",
        "Tartózkodási cím közterület név", "Tartózkodási cím közterület jelleg", "Tartózkodási cím házszám",
        "Tartózkodási cím pontosítás", "Szakmai gyakorlati évek száma", "E-mail cím",
        "Adatkezelő intézmény OM azonosítója", "Adatkezelő intézmény neve", "Adatkezelő intézmény címe",
        "Közoktatási intézmény neve", "Közoktatási intézmény székhelye", "OM azonosító",
        "Kiemelt feladatellátási hely", "Vezetői beosztás", "Jogviszony státusza", "Jogviszony létrejötte",
        "Jogviszony megszűnte", "Jogviszony típusa", "Besorolási kategória", "Fizetési osztály", "Pótlék",
        "Munkakör-kategória", "Munkakör", "Tantárgy", "Szakképzettségek, végzettségek", "Tudományos fokozatok",
        "Pedagógus szakvizsgák", "Egyéb továbbképzések", "A Belügyminisztérium személyiadat- és lakcímnyilvántartásában beazonosított",
        "Utolsó személyiadat- és lakcímnyilvántartás frissítés időpontja"
    );

    // excel rows
    public static final Integer FIRST_ROW_IN_EXCEL = 0;
}
