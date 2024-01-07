package rolling.s3;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

class FileUploadException extends CustomException {

    static final CustomException EXCEPTION = new FileUploadException();

    private FileUploadException() {
        super(ErrorCode.FILE_UPLOAD_ERROR);
    }

}