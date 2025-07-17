package spring_reactive.com.dto.paginationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponseDto<T>{
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
    private List<T> data;
}
