package rolling.domain.company.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class DuplicatedCompanyNameException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedCompanyNameException();

    private DuplicatedCompanyNameException() {
        super(ErrorCode.DUPLICATED_COMPANY_NAME);
    }

}