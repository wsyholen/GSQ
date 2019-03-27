package com.sixtyrobbers.GSQ.fourm.dao.forum;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.TestDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto.TestDTO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.TestParam;

import java.util.List;

/**
 * <pre>
 * Explain: 测试DAO
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:47
 * Version: V1.0
 * </pre>
 */
public interface TestDAO {

    TestDO selectTestDO(TestParam testParam);

    int selectTestDTOCount(TestParam testParam);

    List<TestDTO> selectTestDTO(TestParam testParam);
}
