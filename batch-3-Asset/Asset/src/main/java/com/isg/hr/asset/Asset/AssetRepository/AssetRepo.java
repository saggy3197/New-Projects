package com.isg.hr.asset.Asset.AssetRepository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isg.hr.asset.Asset.AssetEntity.AssetEntity;

@Repository
public interface AssetRepo extends JpaRepository<AssetEntity,Integer>{

    
}
