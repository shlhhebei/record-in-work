package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import util.FileOperateUtil;


@Controller
@RequestMapping(value = "/fileOperate")
public class FileOperateController {
 
	/*TODO ����ModelAndView��ʱ���޷�ת���ƶ�ҳ�棬����ȥ��@RequestMapping�Ĳ�����valueֵ������·��*/
    @RequestMapping(value = "/to_upload",method = RequestMethod.POST)
    public String toUpload() {
      System.out.println("come to toUpload!");
      return "fileOperate/upload";
    }
    
    /*@RequestMapping(value = "/to_upload",method = RequestMethod.POST)
    public ModelAndView toUpload() {
      System.out.println("come to toUpload!");
      return new ModelAndView("fileOperate/upload") ;
    }*/
 
    /**
    * �ϴ��ļ�
    * @param request
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/upload")
    public ModelAndView upload(HttpServletRequest request) throws Exception {
    	System.out.println("come to upload!");
 
        Map<String, Object> map = new HashMap<String, Object>();
 
        // ����
        String[] alaises = ServletRequestUtils.getStringParameters(request,"alais");
 
        String[] params = new String[] { "alais" };
        Map<String, Object[]> values = new HashMap<String, Object[]>();
        values.put("alais", alaises);
 
        List<Map<String, Object>> result = FileOperateUtil.upload(request,params, values);
 
        map.put("result", result);
 
        return new ModelAndView("fileOperate/list", map);
    }
 
    /**
    * ����
    * @param attachment
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "download")
    public ModelAndView download(HttpServletRequest request,
          HttpServletResponse response) throws Exception {
 
      String storeName = "201205051340364510870879724.zip";
      String realName = "Java���ģʽ.zip";
      String contentType = "application/octet-stream";
 
      FileOperateUtil.download(request, response, storeName, contentType,
              realName);
 
      return null;
    }
}