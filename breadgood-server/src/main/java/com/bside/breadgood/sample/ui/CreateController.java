package com.bside.breadgood.sample.ui;

import com.bside.breadgood.apifirstdesign.apis.RecruitCreateApi;
import com.bside.breadgood.apifirstdesign.models.RecruitCreateModel;
import com.bside.breadgood.apifirstdesign.models.RecruitModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class CreateController implements RecruitCreateApi {
    @Override
    public ResponseEntity<RecruitModel> createRecruit(RecruitCreateModel body) {

        RecruitModel model = new RecruitModel();
        if (body.getDeadlineDate().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
