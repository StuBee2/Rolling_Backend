package rolling.domain.member.exception;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

public class DuplicatedNicknameException extends CustomException {

    public static final CustomException EXCEPTION = new DuplicatedNicknameException();

    private DuplicatedNicknameException() {
        super(ErrorCode.DUPLICATED_NICKNAME);
    }

}