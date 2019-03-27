package com.sixtyrobbers.GSQ.fourm.service.forumService.forumServiceImpl;

import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dbdo.TestDO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.dto.TestDTO;
import com.sixtyrobbers.GSQ.fourm.dao.entity.fourm.param.TestParam;
import com.sixtyrobbers.GSQ.fourm.dao.forum.TestDAO;
import com.sixtyrobbers.GSQ.fourm.service.entity.PageInfo;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.TestReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.TestRes;
import com.sixtyrobbers.GSQ.fourm.service.forumService.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Explain: 测试Impl
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:48
 * Version: V1.0
 * </pre>
 */
@Service
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestDAO testDAO;

    @Value("${test.properties}")
    private String testProperties;


    @Override
    public PageInfo<TestRes> testForPage(TestReq testRequest) {
        TestParam testParam = new TestParam();
        testParam.setStartRow((testRequest.getPageNum() - 1) * testRequest.getPageSize());
        testParam.setPageSize(testRequest.getPageSize());
        int totalCount = testDAO.selectTestDTOCount(testParam);
        List<TestRes> resultList = new ArrayList<>();
        if (totalCount > 0) {
            List<TestDTO> list = testDAO.selectTestDTO(testParam);
            for (TestDTO testDTO : list) {
                TestRes response = new TestRes();
                response.setId(testDTO.getId());
                response.setName(testDTO.getName());
                resultList.add(response);
            }
        }
        PageInfo<TestRes> result = new PageInfo<>(testRequest.getPageNum(), testRequest.getPageSize(), totalCount, resultList);
        return result;
    }

    @Override
    public TestRes test(TestReq testRequest) {
        logger.info("properties value service：" + testProperties);
        logger.info("service process start-------------业务层开始处理---");
        TestParam testParam = new TestParam();
        testParam.setName(testRequest.getName());
        TestDO testDO = testDAO.selectTestDO(testParam);
        TestRes testResponse = new TestRes();
        if (null != testDO) {
            testResponse.setId(testDO.getId());
        }
        testResponse.setName(testRequest.getName());
        return testResponse;
    }


}
