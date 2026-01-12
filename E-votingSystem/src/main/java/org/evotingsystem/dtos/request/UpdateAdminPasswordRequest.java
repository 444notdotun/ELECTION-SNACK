package org.evotingsystem.dtos.request;

import lombok.Data;

@Data
public class UpdateAdminPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
