package of_f.of_f_spring.domain.entity.store;

import lombok.extern.slf4j.Slf4j;
import of_f.of_f_spring.domain.exception.StoreException;
import of_f.of_f_spring.domain.exception.StoreExceptionEnum;

@Slf4j
public class StoreStatusCheck {
    private static final int ACTIVATE = 0;
    private static final int DISABLED = 1;
    private static final int REQ_APPLICATION = 5;
    private static final int LOCKED = 8;
    private static final int SUSPENSION = 9;

    public void checkStoreStatus(int status) {
        switch (status) {
            case ACTIVATE:
                log.info("가맹점의 정상적인 접근");
                break;
            case DISABLED:
                throw new StoreException(StoreExceptionEnum.DISABLE_STORE);
            case REQ_APPLICATION:
                throw new StoreException(StoreExceptionEnum.REQ_APPLICATION_STORE);
            case LOCKED:
                throw new StoreException(StoreExceptionEnum.LOCKED_STORE);
            case SUSPENSION:
                throw new StoreException(StoreExceptionEnum.SUSPENSION_STORE);
            default:
                throw new StoreException(StoreExceptionEnum.CAN_NOT_USE_STORE_INFO);

        }
    }

}
