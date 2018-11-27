package cn.ac.sec.web.login;

import cn.ac.sec.entity.watch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/t9device")
public class T9DataController {
    private static final Logger logger = LoggerFactory.getLogger (T9DataController.class);

    @RequestMapping(value = "/T28", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t28(@RequestBody HealthHr healthHr) {
        logger.info (healthHr.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T30", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t30(@RequestBody HealthPe healthPe) {
        logger.info (healthPe.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T50", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t50(@RequestBody HealthTe healthTe) {
        logger.info (healthTe.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T62", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t62(@RequestBody HealthBp healthBp) {
        logger.info (healthBp.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T91", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t91(@RequestBody HealthSl healthSl) {
        logger.info (healthSl.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T97", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t97(@RequestBody HealthBs healthBs) {
        logger.info (healthBs.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T86", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t86(@RequestBody Location location) {
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T94", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t94(@RequestBody Location location) {
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T82", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t85(@RequestBody BatteryLow batteryLow) {
        logger.info (batteryLow.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T85", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t85(@RequestBody Location location) {
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T89", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t89(@RequestBody Location location) {
        logger.info (location.toString ());
        return new BaseResp ();
    }
}
