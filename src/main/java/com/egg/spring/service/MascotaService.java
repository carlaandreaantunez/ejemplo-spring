package com.egg.spring.service;

import com.egg.spring.entity.Mascota;
import com.egg.spring.entity.Usuario;
import com.egg.spring.repository.MascotaRepository;
import com.egg.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void crear(String nombre, Long dniDueno) {
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setDueno(usuarioRepository.findById(dniDueno).orElse(null));

        mascotaRepository.save(mascota);
    }

    @Transactional
    public void modificar(String id, String nombre) {
        mascotaRepository.modificar(id, nombre);
    }

    @Transactional(readOnly = true)
    public List<Mascota> buscarTodas() {
        return mascotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Mascota buscarPorId(String id) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        return mascotaOptional.orElse(null);
    }

    @Transactional
    public void eliminar(String id) {
        mascotaRepository.deleteById(id);
    }

}
