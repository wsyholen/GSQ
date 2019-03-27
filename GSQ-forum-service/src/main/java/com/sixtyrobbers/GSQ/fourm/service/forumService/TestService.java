package com.sixtyrobbers.GSQ.fourm.service.forumService;

import com.sixtyrobbers.GSQ.fourm.service.entity.PageInfo;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.request.TestReq;
import com.sixtyrobbers.GSQ.fourm.service.entity.forum.response.TestRes;

/**
 * <pre>
 * Explain: 测试Service
 * Author: holennnnnn_
 * Create_Time: 2019/3/26 13:44
 * Version: V1.0
 * </pre>
 */
public interface TestService {

    /**
     * <pre>
     * Explain: 测试分页
     * Author: holennnnnn_
     * Create_Time: 2019/3/26 13:44
     * Version: V1.0
     * </pre>
     */
    PageInfo<TestRes> testForPage(TestReq testRequest);

    /**
     * <pre>
     * Explain: 测试查询
     * Author: holennnnnn_
     * Create_Time: 2019/3/26 13:44
     * Version: V1.0
     * </pre>
     */
    TestRes test(TestReq testRequest);

}
