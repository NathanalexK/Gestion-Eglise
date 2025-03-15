package org.example.fiangonana.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PageNavigation {
    private Integer numeroPage = 0;
    private Integer taillePage = 200;
    private Integer totalPage;
    private Long nombreElements;

    public PageNavigation(){}

    public PageNavigation(Page page) {
        setNumeroPage(page.getNumber());
        setTaillePage(page.getSize());
        setTotalPage(page.getTotalPages());
        setNombreElements(page.getTotalElements());
    }

    public Integer getNumeroPage() {
        if(this.numeroPage == null) return 0;
        return this.numeroPage;
    }
}
