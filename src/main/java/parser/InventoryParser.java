package parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class InventoryParser{
    private final String fileName;
    public InventoryParser(String fileName){
        this.fileName = fileName;
    }
    public List<String> readFile() {
        List<String> data = null;
        try(InputStream fileStream = InventoryParser.class.getResourceAsStream(fileName)){
            if(fileStream != null){
                byte[] fileBytes = fileStream.readAllBytes();
                String fileData = new String(fileBytes);
                data = Arrays.stream(fileData.split("\n")).skip(1).map(String::trim).toList();
            }
        }catch (IOException ioException){
            System.out.println("abou el feef");
        }
        return data;
    }
}
