package rolling.navernewsclient;

import rolling.domain.common.error.CustomException;
import rolling.domain.common.error.ErrorCode;

class NewsClientException extends CustomException {

    static final CustomException EXCEPTION = new NewsClientException();

    private NewsClientException() {
        super(ErrorCode.NEWS_CLIENT);
    }

}