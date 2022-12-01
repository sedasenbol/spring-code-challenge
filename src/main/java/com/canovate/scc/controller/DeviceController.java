package com.canovate.scc.controller;

import com.canovate.scc.model.Device;
import com.canovate.scc.model.data.search.DeviceSpecificationsBuilder;
import com.canovate.scc.repository.DeviceJpaRepository;
import com.canovate.scc.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping
public class DeviceController {

    private DeviceService deviceService;
    private DeviceJpaRepository deviceJpaRepository;

    @Autowired
    public DeviceController(DeviceService deviceService, DeviceJpaRepository deviceJpaRepository) {
        this.deviceService = deviceService;
        this.deviceJpaRepository = deviceJpaRepository;
    }

    @GetMapping(value = "/list{search}")
    @ResponseBody
    public List<Device> listDevices(@PathVariable String search) {
        DeviceSpecificationsBuilder builder = new DeviceSpecificationsBuilder();
        Pattern pattern = Pattern.compile("[?][A-Za-z0-9].*(=)[A-Za-z0-9].*[&,].*");
        Matcher matcher = pattern.matcher(search + "&");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        System.out.println("1");

        Specification<Device> spec = builder.build();
        return deviceJpaRepository.findAll(spec);
    }

}
//    @GetMapping
//    @ResponseBody
//    public Page<Device> search(@RequestParam(value = "list") String search, Pageable pageable)
//    {
//        Page<Device> devices = null;
//        DeviceSpecificationsBuilder builder = new DeviceSpecificationsBuilder();
//        Pattern pattern = Pattern.compile("[A-Za-z0-9].*(=)[A-Za-z0-9].*[&,].*");
//        Matcher matcher = pattern.matcher(search + "&");
//        while (matcher.find()) {
//            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
//        }
//        Specification<Device> spec = builder.build();
//        devices=deviceJpaRepository.findAll(spec,pageable);
//
//        return devices;

//    CropMasterDTOMapper mapper = Mappers.getMapper(CropMasterDTOMapper.class);
//    List<CropMasterDTO> cropMasterDTOs = mapper.cropMasterToDTOs(cropMasters.getContent());
//            return new PageImpl<>(cropMasterDTOs, pageable ,cropMasters.getTotalElements());


//    @RequestMapping(value = "/devices",
//                    method = GET)
//    Page<Device> devicesPageable(Pageable pageable) {
//        return deviceJpaRepository.findAll(pageable);
//    }
//
//
//    @RequestMapping(
//            value = "/devices",
//            params = {"brand"},
//            method = GET)
//    @ResponseBody
//    public Page<Device> findByBrand(@RequestParam(defaultValue = "0") Integer page,
//                                    @RequestParam(defaultValue = "5") Integer size,
//                                    @RequestParam String brand) {
//
//        return deviceService.findByBrand(brand,page,size);
//
//    }
//
//    @RequestMapping(
//            value = "/devices",
//            params = {"model"},
//            method = GET)
//    @ResponseBody
//    public Page<Device> findByModel(@RequestParam(defaultValue = "0") Integer page,
//                                    @RequestParam(defaultValue = "5") Integer size,
//                                    @RequestParam String model) {
//
//        return deviceService.findByModel(model,page,size);
//
//    }
//
//    @RequestMapping(
//            value = "/devices",
//            params = {"brand", "model"},
//            method = GET)
//    @ResponseBody
//    public Page<Device> findByBrandAndModel(@RequestParam(defaultValue = "0") Integer page,
//                                    @RequestParam(defaultValue = "5") Integer size,
//                                    @RequestParam String brand,
//                                    @RequestParam String model) {
//
//        return deviceService.findByBrandAndModel(brand,model, page,size);
//
//    }

