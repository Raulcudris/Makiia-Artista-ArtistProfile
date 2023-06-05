package com.makiia.crosscutting.translate;
import com.makiia.crosscutting.domain.model.EntyApjmaeshomepamaDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyApjmaeshomepama;
import com.makiia.crosscutting.utils.GsonUtil;
import org.springframework.stereotype.Component;
@Component
public class EntyApjmaeshomepamaDtoToEntityTranslate implements Translator<EntyApjmaeshomepamaDto, EntyApjmaeshomepama> {

    @Override
    public EntyApjmaeshomepama translate(EntyApjmaeshomepamaDto input) throws EBusinessException {
        return GsonUtil.getGson(false)
                .fromJson(GsonUtil.getGson().toJson(input), EntyApjmaeshomepama.class);
    }
}
