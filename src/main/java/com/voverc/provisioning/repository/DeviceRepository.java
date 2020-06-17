package com.voverc.provisioning.repository;

import com.voverc.provisioning.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    @Query("select d from Device d where d.macAddress = :mac_address")
    Device findByMacAddress(@Param("mac_address") String macAddress);
}
