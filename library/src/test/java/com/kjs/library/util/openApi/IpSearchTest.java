package com.kjs.library.util.openApi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class IpSearchTest {
	
	@InjectMocks
	private IpSearch ipSearch;
	
	//@Test
	public void 대출Test() {
			
		try {
			ipSearch.국내아이피다("172.31.36.199");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}


}
