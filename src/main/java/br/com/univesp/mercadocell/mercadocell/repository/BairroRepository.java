package br.com.univesp.mercadocell.mercadocell.repository;

import br.com.univesp.mercadocell.mercadocell.model.Bairro;
import br.com.univesp.mercadocell.mercadocell.model.Estado;
import br.com.univesp.mercadocell.mercadocell.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BairroRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    static final String COLUNA_COD_BAIRRO ="COD_BAIRRO";
    static final String COLUNA_NME_BAIRRO ="NME_BAIRRO";

    static final String CONSULTA_BAIRRO =
            "SELECT  B.COD_BAIRRO, B.NME_BAIRRO, M.COD_MUNICIPIO, M.NME_MUNICIPIO, E.COD_ESTADO, E.NME_ESTADO, E.SGL_UF " +
            " FROM BAIRRO B " +
            " INNER JOIN MUNICIPIO M ON M.COD_MUNICIPIO = B.COD_MUNICIPIO " +
            " INNER JOIN ESTADO E  ON M.COD_ESTADO = E.COD_ESTADO " ;

    static final String FILTRO_CODIGO_BAIRRO = " WHERE COD_BAIRRO = ?";
    static final String FILTRO_NOME_BAIRRO = " WHERE NME_BAIRRO = ?";
    static final String INSERT_BAIRRO = " INSERT INTO BAIRRO (NME_BAIRRO, COD_MUNICIPIO) VALUES (?, ?) ";
    static final String UPDATE_BAIRRO = " UPDATE BAIRRO SET NME_BAIRRO = ?, COD_MUNICIPIO = ? ";
    static final String DELETE_BAIRRO = " DELETE FROM BAIRRO " ;

    public void cadastrarBairro(Bairro bairro) {
        jdbcTemplate.update(
                INSERT_BAIRRO,
                bairro.getNomeBairro(),
                bairro.getMunicipio().getCodMunicipio()
        );
    }

    public Bairro buscarBairroPorId(int idBairro) {
        return jdbcTemplate.queryForObject(CONSULTA_BAIRRO
                , (resultSet, rowNum) ->
                        new Bairro(
                                resultSet.getInt(COLUNA_COD_BAIRRO),
                                resultSet.getString(COLUNA_NME_BAIRRO),
                                new Municipio(
                                        resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                        resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                        new Estado(
                                                resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO),
                                                resultSet.getString(EstadoRepository.COLUNA_SGL_UF)
                                        )
                                )

                        ),
                idBairro
        );
    }

    public Bairro buscarBairroPorNome(String nomeBairro){
        return jdbcTemplate.queryForObject(
                    CONSULTA_BAIRRO + FILTRO_NOME_BAIRRO
                , (resultSet, rowNum) ->
                        new Bairro(
                                resultSet.getInt(COLUNA_COD_BAIRRO),
                                resultSet.getString(COLUNA_NME_BAIRRO),
                                new Municipio(
                                        resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                        resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                        new Estado(
                                                resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO),
                                                resultSet.getString(EstadoRepository.COLUNA_SGL_UF)
                                        )
                                )
                        ),
                nomeBairro
        );
    }

    public List<Bairro> listarBairros() {
        return jdbcTemplate.query(CONSULTA_BAIRRO
        , (resultSet, rowNum) ->
                        new Bairro(
                                resultSet.getInt(COLUNA_COD_BAIRRO),
                                resultSet.getString(COLUNA_NME_BAIRRO),
                                new Municipio(
                                        resultSet.getInt(MunicipioRepository.COLUNA_COD_MUNICIPIO),
                                        resultSet.getString(MunicipioRepository.COLUNA_NME_MUNICIPIO),
                                        new Estado(
                                                resultSet.getInt(EstadoRepository.COLUNA_COD_ESTADO),
                                                resultSet.getString(EstadoRepository.COLUNA_NME_ESTADO),
                                                resultSet.getString(EstadoRepository.COLUNA_SGL_UF)
                                        )
                                )
                        )
        );
    }

    public void atualizarBairro(Bairro bairro) throws DataIntegrityViolationException {
        jdbcTemplate.update(
                UPDATE_BAIRRO + FILTRO_CODIGO_BAIRRO,
                bairro.getNomeBairro(),
                bairro.getMunicipio().getCodMunicipio(),
                bairro.getCodBairro()
        );
    }

    public void deletarBairro(int idBairro) throws DataIntegrityViolationException{
        jdbcTemplate.update(
                DELETE_BAIRRO + FILTRO_CODIGO_BAIRRO,
                idBairro
        );
    }
}