package com.fithub.services.training.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fithub.services.training.api.model.external.MembershipPaymentReportResponse;

@FeignClient(name = "membership-service")
public interface MembershipServiceClient {

    @GetMapping("/membership/report")
    ResponseEntity<MembershipPaymentReportResponse> getMembershipPaymentReport(@RequestHeader("Authorization") String coachUuid,
            @RequestParam("client_uuid") String clientUuid);

}