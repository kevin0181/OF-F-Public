package of_f.of_f_spring.service.user;

import of_f.of_f_spring.domain.entity.user.Role;
import of_f.of_f_spring.domain.mapper.user.RoleMapper;
import of_f.of_f_spring.dto.user.RoleDTO;
import of_f.of_f_spring.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public void createRole(RoleDTO roleDTO) throws Exception { // 권한 생성

//        if (roleDTO.getRoleName().equals(""))
//            throw new Exception("빈 값이 들어왔습니다.");
//
//        if (roleDTO.getRoleName().length() > 20)
//            throw new Exception("권한의 최대 크기는 max = 20");

        Role role = RoleMapper.instance.RoleDTOTORole(roleDTO);

        roleRepository.save(role);

    }

    public List<RoleDTO> getListRole() { // 권한 가져오기
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = RoleMapper.instance.RolesTORoleDTOs(roles);
        return roleDTOS;
    }

}
