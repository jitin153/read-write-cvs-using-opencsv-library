package com.demo.opencsv;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class OpenCsvExample {

	private static final String FILE_PATH="src/main/resources/csv/test-csv-data.csv";
	
	public static void main(String[] args) {
		String[] header = { "ID", "NAME", "AGE" };
		String[] row1 = { "1", "JACK", "27" };
		String[] row2 = { "2", "PETER", "31" };
		String[] row3 = { "3", "HARRY", "24" };
		List<String[]> data = Arrays.asList(header, row1, row2, row3);
		writeCsvFile(data);
		readCsvFile(FILE_PATH).forEach(row->{
			System.out.println(Arrays.toString(row));
		});
		readCsvDataFromBytes(writeCsvDataIntoBytes(data)).forEach(row->{
			System.out.println(Arrays.toString(row));
		});
	}

	private static void writeCsvFile(List<String[]> dataToBeWritten) {
		try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
			writer.writeAll(dataToBeWritten);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static byte[] writeCsvDataIntoBytes(List<String[]> dataToBeWritten) {
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream))) {
			writer.writeAll(dataToBeWritten);
			writer.close();
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private static List<String[]> readCsvFile(String fileToBeRead) {
		try (CSVReader reader = new CSVReader(new FileReader(fileToBeRead))) {
			return reader.readAll();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private static List<String[]> readCsvDataFromBytes(byte[] data) {
		try (CSVReader reader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(data)))) {
			return reader.readAll();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
