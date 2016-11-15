/**
 * Created by lawlor59 on 01/11/16.
 */
import org.apache.commons.csv.*

List columnNames = [ "Gender", "County", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
                     "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"]


// specifying the source file
FileReader sourceFile = new FileReader("JoinedData.csv");
BufferedReader  csvReader = new BufferedReader(sourceFile)

// parse the csv file to memory
CSVParser csv = new CSVParser(csvReader, CSVFormat.EXCEL.withHeader())

// For writing to the file
FileWriter fileWriter = new FileWriter(new File("populationfullyfilteredoutput.csv"))
CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withRecordSeparator("\n"))


for (record in csv) {
    for (int i = 2; i < columnNames.size(); i++){
        PopulationRecord pr = new PopulationRecord(record[0], record[1], columnNames.getAt(i), record[i])
        csvPrinter.print(pr.gender)
        csvPrinter.print(pr.county)
        csvPrinter.print(pr.population)
        csvPrinter.print("01/01/" + pr.year)
        csvPrinter.println()
    }
}

try {
    fileWriter.flush();
    fileWriter.close();
    csvPrinter.close();
} catch (IOException e) {
    System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
    e.printStackTrace();
}

public class PopulationRecord{
    def gender
    def county
    def year
    def population

    public PopulationRecord(gender, county, year, population) {
        this.gender = gender
        this.county = county
        this.year = year
        this.population = population
    }
}
