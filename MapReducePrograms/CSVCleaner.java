package com.airbnb.mr;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvBindByName;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CSVCleaner {



    public class CSVHotel {
        @CsvBindByName(column = "id")
        private String id;

        @CsvBindByName(column = "name")
        private String name;

        @CsvBindByName(column = "host_id")
        private String host_id;

        @CsvBindByName(column = "host_name")
        private String host_name;

        @CsvBindByName(column = "neighbourhood_group")
        private String neighbourhood_group;

        @CsvBindByName(column = "neighbourhood")
        private String neighbourhood;

        @CsvBindByName(column = "latitude")
        private String latitude;

        @CsvBindByName(column = "longitude")
        private String longitude;

        @CsvBindByName(column = "room_type")
        private String room_type;


        @CsvBindByName(column = "price")
        private String price;

        @CsvBindByName(column = "minimum_nights")
        private String minimum_nights;

        @CsvBindByName(column = "number_of_reviews")
        private String number_of_reviews;

        @CsvBindByName(column = "last_review ")
        private String last_review ;

        @CsvBindByName(column = "reviews_per_month")
        private String reviews_per_month;

        @CsvBindByName(column = "calculated_host_listings_count")
        private String calculated_host_listings_count ;

        @CsvBindByName(column = "availability_365")
        private String availability_365 ;
    }
    private static final String SAMPLE_CSV_FILE_PATH = "D:\\BigDataProgram\\BigDataAnalytics\\Assignment\\Quection2.1_2.2\\ProjectResource\\listings.csv";
    private static final String STRING_ARRAY_SAMPLE = "D:\\BigDataProgram\\BigDataAnalytics\\Assignment\\Quection2.1_2.2\\ProjectResource\\stackapps.com\\comments\\listingsnew.csv";
    public static void main(String[] args) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));

        CSVWriter csvWriter = new CSVWriter(writer,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END
        );

        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);

            String[] nextRecord;
            int count=0;


            while ((nextRecord = csvReader.readNext()) != null) {
                    String[] data1 ={CleanValues(nextRecord[0]),CleanValues(nextRecord[1]),
                            CleanValues(nextRecord[2]),CleanValues(nextRecord[3]),CleanValues(nextRecord[4]),CleanValues(nextRecord[5]),
                            CleanValues(nextRecord[6]),CleanValues(nextRecord[7]),CleanValues(nextRecord[8]),CleanValues(nextRecord[9]),CleanValues(nextRecord[10]),
                            CleanValues(nextRecord[11]),CleanValues(nextRecord[12]),CleanValues(nextRecord[13]),CleanValues(nextRecord[14]),CleanValues(nextRecord[15])};
                    csvWriter.writeNext(data1);

                count++;
            }
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();

        } finally {
            csvWriter.close();
        }
    }

    public static String CleanValues(String value){
       return  value.replace(",","").replace("\n","");
    }





}


