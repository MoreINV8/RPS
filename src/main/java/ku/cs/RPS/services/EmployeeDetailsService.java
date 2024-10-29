package ku.cs.RPS.services;

import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private DBRepository dbRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (!dbRepository.isExistEmployeeByEmail(email)) {
            throw new UsernameNotFoundException("ไม่พบพนักงาน");
        }


        Employee employee = dbRepository.findEmployeeByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(employee.getEmployeeDepartment()));

        return new org.springframework.security.core.userdetails.User(
                employee.getEmployeeEmail(), employee.getEmployeePassword(), authorities);
    }
}
