package br.com.wpsistemas.exemplospmvcth.controller.converter;

import br.com.wpsistemas.exemplospmvcth.modelo.entidade.Estado;
import br.com.wpsistemas.exemplospmvcth.modelo.repository.EstadoRepository;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author wender
 */
public class EstadoConverter extends PropertyEditorSupport {

    
    EstadoRepository estadoRepository;

    public EstadoConverter(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        Long idEstado = new Long(id);
        Estado estado = estadoRepository.findById(idEstado).get();
        this.setValue(estado);
    }

}
