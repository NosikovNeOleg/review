package com.rdm.rdm.implementations;

import Animals.AbstractAnimal;
import Interfaces.Animal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ResultReader {
    // HW-6

    FilesConfig filesConfig = new FilesConfig();

    public void PrintAnimalJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TypeReference<Set<AbstractAnimal>> typeRef
                = new TypeReference<>() {
        };

        Set<AbstractAnimal> animalsSet;
        String path = filesConfig.GetStringPath(2);
        File file = new File(path);
        try {
            animalsSet = objectMapper.readValue(file, typeRef);
            for (Animal key : animalsSet) {
                byte[] decodedBytes = Base64.getDecoder().decode(key.getSecretInformation());
                String decodedString = new String(decodedBytes);
                key.setSecretInformation(decodedString);
                System.out.println("Декодированное животное: " + key.toStringWithSecretInformation());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void LogDataRowsCountPrint() {
        int logDataRowsCount = LogDataRowsCount();
        System.out.println("Всего строк в файле лога: " + logDataRowsCount);
    }

    public int LogDataRowsCount() {
        Path path = filesConfig.GetPath(1);
        int result = 0;
        int spaceIndex;

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                spaceIndex = line.indexOf(' ');
                result = Integer.parseInt(line.substring(0, spaceIndex));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

}
