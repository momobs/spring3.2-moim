package moim.common.vo;

import lombok.Data;

@Data
public class FileVO {
	String idx;
	String file_group;
	String stored_file_name;
	String original_file_name;
	String file_size;
	String del_yn;
	String create_date;
	String create_user;
	String delete_date;
	String delete_user;
}
