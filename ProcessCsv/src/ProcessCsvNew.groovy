import org.apache.commons.csv.*

List columnNames = [ "Gender", "Office", "County", "01/01/2002", "01/01/2003", "01/01/2004", "01/01/2005", "01/01/2006", "01/01/2007",	"01/01/2008",
                     "01/01/2009", "01/01/2010", "01/01/2011", "01/01/2012", "01/01/2014", "01/01/2015", "01/01/2016"]


// specifying the source file
FileReader sourceFile = new FileReader("NewUnemploymentFiltered.csv");
BufferedReader  csvReader = new BufferedReader(sourceFile)

// parse the csv file to memory
CSVParser csv = new CSVParser(csvReader, CSVFormat.EXCEL.withHeader())

// For writing to the file
FileWriter fileWriter = new FileWriter(new File("testoutput.csv"))
CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withRecordSeparator("\n"))


for (record in csv) {
    for (int i = 3; i < columnNames.size(); i++){
        Record1 r = new Record1(record[0], record[1], record[2], columnNames.getAt(i), record[i])
        csvPrinter.print(r.gender)
        csvPrinter.print(r.office)
        csvPrinter.print(r.county)
        csvPrinter.print(r.date)
        csvPrinter.print(r.figure)
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

public class Record1{
    def gender
    def office
    def county
    def date
    def figure

    public Record1(gender, office, county, date, figure) {
        this.gender = gender
        this.office = office
        this.county = county
        this.date = date
        this.figure = figure
    }
}
