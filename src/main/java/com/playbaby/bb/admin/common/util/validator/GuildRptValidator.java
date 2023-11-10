package com.playbaby.bb.admin.common.util.validator;

import com.playbaby.bb.admin.common.entity.io.GuildRptIO;
import com.playbaby.bb.admin.common.entity.io.PerfAssessmentIApprovalIO;
import com.playbaby.bb.admin.common.exception.APIException;
import com.playbaby.bb.admin.common.util.Rationalizer;

public class GuildRptValidator {

    public static void validateReport(GuildRptIO io) {
        CommonValidator.validateDate(io);
        CommonValidator.validateSort(io);
        // let's set some default values
        io.setPageInt(1);
        io.setGuildlessInt(0);
        Rationalizer.rationalizeInt(io, new String[]{ "page", "guild", "gm", "guildless", "uid" }, false);
    }

    public static void validatePerfAssessment(PerfAssessmentIApprovalIO io) throws APIException {
        if (io == null)
            throw APIException.getInstance(200010, "PerfAssessmentIApprovalIO cannot be null");
        if (io.getUid() == null)
            throw APIException.getInstance(200011, "anchor uid must be defined");
        if (io.getDate() == null)
            throw APIException.getInstance(200012, "assessment approval date must be defined");
        if (!CommonValidator.isDateValid(io.getDate()))
            throw APIException.getInstance(200013, "assessment approval date be in yyyyMMdd format");

    }
}
