package com.sonepar.mownow.domain.user_cases;

import com.sonepar.mownow.domain.entities.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MowerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MowerService.class);

    public List<Mower> getMowerList(String fileName) {
        // load file
        BufferedReader reader;
        var mowerList = new ArrayList<Mower>();
        try {
            // read a file
            reader = new BufferedReader(new FileReader(fileName));
            String upperCornerLine = parseLine(reader.readLine());
            String mowerInformationLine = parseLine(reader.readLine());
            while (mowerInformationLine != null) {
                mowerList.add(new Mower(upperCornerLine, mowerInformationLine, parseLine(reader.readLine())));
                // read next line
                mowerInformationLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            LOGGER.error("error to open file, details:", e.getCause());
        }
        return mowerList;
    }

    public Mower orienterMower(Mower mower) {

        return mower.orient();
    }

    private String parseLine(String line) throws IOException {
        return Optional.of(line).orElseThrow( ()-> new IllegalArgumentException("Error to parse file"));
    }


}
