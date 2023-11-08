package com.isg.hr.asset.Asset.AssetController;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isg.hr.asset.Asset.AssetEntity.AssetEntity;
import com.isg.hr.asset.Asset.AssetRepository.AssetRepo;

import com.isg.hr.asset.Asset.ResponseBean.AssetResponseBean;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/asset")
@Tag(name = "Asset Controller ",description = "Contains rest services of asset")


public class AssetController {


    private static final Logger log = LogManager.getLogger(AssetController.class);
    @Autowired AssetRepo  assrepo;
    @Autowired AssetResponseBean response;


    public final MessageSource messageSource;
    public AssetController(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }
    @GetMapping("/asset/get")
    @Operation(summary = "Returns the all asset information")
    public ResponseEntity<Object> getAll(){
        log.info("Start of Get request to fetch list");
        java.util.List<AssetEntity> list = assrepo.findAll();
        response.setErrorCode(null);
        response.setErrorDesc(null);
        response.setData(list);
        log.info("End of the Get request");
        return new ResponseEntity<Object>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns the Particular Id asset information")
    public ResponseEntity<Object> findAEntity(@PathVariable("id") int id){
        log.info("Start of Get request to fetch asset from -- " +id+"id");
        Optional<AssetEntity> foundAsset = assrepo.findById(id);
        if(foundAsset.isPresent()){

            response.setErrorCode(null);
            response.setErrorCode(null);
            response.setData(foundAsset);
            log.info("End of the Get Request");
            return new ResponseEntity<Object>(foundAsset,HttpStatus.OK);
        }
        else{

           String deschMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
           String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
           response.setErrorCode(messageCode);
           response.setErrorDesc(deschMessage+"---"+id); 
           response.setData(null);
           log.info("End of the Get Request");
           return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/addasset")
    @Operation(summary = "Add the asset information")
    public ResponseEntity<Object> addAsset(@RequestBody AssetEntity addAssetEntity,
           @Param("CratedBy") String createdby){

        log.info("Start of Post request by -- ");
        try{
            if(assrepo.findById(addAssetEntity.getId()).isPresent()){

                String descMessage = messageSource.getMessage("ERROR_DESC02", null, LocaleContextHolder.getLocale());
                String messageCode = messageSource.getMessage("ERROR_ID02", null, LocaleContextHolder.getLocale());
                response.setErrorCode(messageCode);
                response.setErrorDesc(descMessage);
                response.setData(null);
                log.error(descMessage + " at --" + addAssetEntity.getId() + "empcode");
                log.info("End of Post request by --"+createdby);
                return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
            }      
            else{

                   response.setErrorCode(null);
                   response.setErrorDesc(null);
                   response.setData(addAssetEntity);
                   log.debug("Created -- " + addAssetEntity);
                   log.info("End of Post request by --"+createdby);
                return new ResponseEntity<Object>(assrepo.save(addAssetEntity), HttpStatus.CREATED);
            }
         } catch(Exception e){
           
            return new ResponseEntity<Object>(addAssetEntity , HttpStatus.BAD_REQUEST);

         }


    }

    @PutMapping("/asset/{id}")
    @Operation(summary = "update the asset information")
         public ResponseEntity<Object>updateAsset(@PathVariable int id , @RequestBody AssetEntity updateAsset,
                @Param("ModifiedBy") String ModifiedBy, @Param("createdby") String createdby){

            log.info("Start the put Reques");
            try{
                Optional<AssetEntity> foundasset = assrepo.findById(id);
                if(foundasset.isPresent()){
                    AssetEntity updateAssetEntity = foundasset.get();
                    updateAssetEntity.setName(updateAsset.getName());
                    updateAssetEntity.setType(updateAsset.getName());
                    updateAssetEntity.setCreatedby(updateAsset.getCreatedby());
                    updateAssetEntity.setCreateddate(updateAsset.getCreateddate());
                    updateAssetEntity.setModifiedby(updateAsset.getModifiedby());
                    updateAssetEntity.setModifieddate(updateAsset.getModifieddate());
                    updateAssetEntity.setEmpcode(updateAsset.getEmpcode());



                    response.setErrorCode(null);
                    response.setErrorDesc(null);
                    response.setData(assrepo);
                    log.debug("Update Employee at"+id+"id  by"+ModifiedBy);
                    log.info("End of the put request");
                    assrepo.save(updateAssetEntity);
                    return new ResponseEntity<Object>("Data Updated", HttpStatus.OK);

                }    
                else{

                    String descMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
                    String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
                    response.setErrorCode(messageCode);
                    response.setErrorDesc(descMessage);
                    response.setData(null);

                    log.debug("new Employee created"+id+"empcode by"+createdby);
                    log.info("End of the put request");
                    return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
                }
            } catch(Exception e){
                return new ResponseEntity<Object>("Asset not found",HttpStatus.NOT_FOUND);
            
            } 

         }
    @PatchMapping("/asset/patch/{id}")
    @Operation(summary = "update the asset information partially")
         public ResponseEntity<Object> patchAsset(@PathVariable int id,@RequestBody Map<Object,Object> perMap,
                @Param("modifiedby") String modifiedby){
            Optional<AssetEntity> asset = assrepo.findById(id);
            if(asset.isPresent()){
                AssetEntity assetpatch = asset.get();
                perMap.forEach((key,value)->{
                    Field field = ReflectionUtils.findRequiredField(AssetEntity.class,(String)key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, assetpatch, value);
                    assrepo.save(assetpatch);    
                });

                        response.setErrorCode(null);
                        response.setErrorDesc(null);
                        response.setData(null);
                        log.debug("Updated employee present at "+id+"empcode by"+modifiedby);
                        log.info("End of Patch request");

                return new ResponseEntity<Object>("patch success",HttpStatus.OK);
                
            }
            else{


                 String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
                 String descMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
                 response.setErrorCode(messageCode);
                 response.setErrorDesc(descMessage +"-- "+id);
                 log.error(descMessage);
                 response.setData(null);
                 log.info("End of Patch request");
                return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
            }
         }
        
    @DeleteMapping("/asset/delete/{id}")
    @Operation(summary = "Delete the asset")
         public ResponseEntity<Object> deleteAsset(@PathVariable int id ){
            if(assrepo.findById(id).isPresent()){
                assrepo.deleteById(id);


                response.setErrorCode(null);
                response.setErrorDesc(null);
                response.setData("deleted");
                log.info("End of the delete Request");
                return new ResponseEntity<Object>(response,HttpStatus.OK);
            }
            else{

                String descMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
                String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
                response.setErrorCode(messageCode);
                response.setErrorDesc(descMessage);
                log.error(descMessage+"---"+id);
                response.setData("deleted");
                log.info("End of the delete Request");
                return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
            }
         }

    
}
