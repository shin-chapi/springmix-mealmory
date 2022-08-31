package com.example.config;

import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean  //Jpeg,JFIFイメージのExif書き込み,更新,削除機能のインタフェース
	public ExifRewriter exifRewriter() {
		return new ExifRewriter();
	}
}
