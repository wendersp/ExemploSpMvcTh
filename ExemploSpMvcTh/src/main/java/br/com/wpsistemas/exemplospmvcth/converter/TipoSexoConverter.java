
package br.com.wpsistemas.exemplospmvcth.converter;

import br.com.wpsistemas.exemplospmvcth.modelo.uteis.TipoSexo;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author wender
 */
public class TipoSexoConverter  implements Converter<String, TipoSexo> {

	@Override
	public TipoSexo convert(String texto) {
		char tipo = texto.charAt(0);
		return tipo == TipoSexo.FEMININO.getDesc() ? TipoSexo.FEMININO : TipoSexo.MASCULINO;
	}
}
