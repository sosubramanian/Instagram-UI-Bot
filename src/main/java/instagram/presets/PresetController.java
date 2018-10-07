package instagram.presets;

import instagram.model.PresetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/presets")
public class PresetController {

    @Autowired
    private PresetService presetService;

    private static final String PRESET_NAME = "presetName";

    @RequestMapping("/list")
    public List<PresetData> getList() {
        return presetService.getAll();
    }

    @RequestMapping("/")
    public PresetData getPresetByCookie(@CookieValue(value = PRESET_NAME, defaultValue = "") String presetName) {
        return presetService.getPreset(presetName);
    }

    @RequestMapping("/{presetName}")
    public PresetData getPresetByName(@PathVariable String presetName, HttpServletResponse response) {
        response.addCookie(new Cookie(PRESET_NAME, presetName));
        return presetService.getPreset(presetName);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addPreset(@Valid @RequestBody PresetData data) {
        presetService.addPreset(data);
        return data.getName() + " preset has been saved successfully!";
    }

}