package com.example.clinicaOdontologicaConORM.serviceTests;

import com.example.clinicaOdontologicaConORM.dto.OdontologoDTO;
import com.example.clinicaOdontologicaConORM.service.impl.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService serviceOdontologoDto;

    private OdontologoDTO o = new OdontologoDTO();

    public void cargarDataSet () {
        o.setNombre("Simon");
        o.setApellido("Simonian");
        o.setMatricula(1234);
        serviceOdontologoDto.insertar(o);
    }

    @Test
    public void borraUnOdontologo() throws Exception {
        cargarDataSet();
        String respuestaEsperada = "Eliminado con éxito";
        String respuesta = serviceOdontologoDto.eliminar(1);
        Assert.assertEquals(respuestaEsperada, respuesta);
    }
}
