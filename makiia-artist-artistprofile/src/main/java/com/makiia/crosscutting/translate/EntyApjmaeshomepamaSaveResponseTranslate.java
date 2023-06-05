package com.makiia.crosscutting.translate;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyApjmaeshomepama;
import com.makiia.crosscutting.utils.GsonUtil;
import org.springframework.stereotype.Component;
@Component
public class EntyApjmaeshomepamaSaveResponseTranslate implements Translator<EntyApjmaeshomepama, EntyApjmaeshomepamaDto> {
    @Override
    public EntyApjmaeshomepamaDto translate(EntyApjmaeshomepama input) throws EBusinessException {
        return GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(input), EntyApjmaeshomepamaDto.class);
    }
}
