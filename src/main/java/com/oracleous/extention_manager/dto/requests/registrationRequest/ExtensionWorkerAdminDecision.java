package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.oracleous.extention_manager.data.model.Stamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionWorkerAdminDecision {
    private String email ;
    private Stamp action;
}
