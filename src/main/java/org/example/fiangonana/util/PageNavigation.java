package org.example.fiangonana.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PageNavigation {
    private Integer numeroPage = 0;
    private Integer taillePage = 50;
    private Integer totalPage;
    private Long nombreElements;
    private Integer numeroPageAffiche;

    public PageNavigation(){}

    public PageNavigation(Page page) {
        setNumeroPage(page.getNumber());
        setTaillePage(page.getSize());
        setTotalPage(page.getTotalPages());
        setNombreElements(page.getTotalElements());
    }

    public Integer getNumeroPage() {
        if(this.getNumeroPageAffiche() != null) return this.getNumeroPageAffiche() - 1;
        if(this.numeroPage == null) return 0;
        return this.numeroPage;
    }

    public boolean hasNext() {
        return this.getNumeroPage() < this.getTotalPage() - 1;
    }

    public boolean hasPrev() {
        return this.getNumeroPage() > 0;
    }

//    public Integer getNumeroPageAffiche() {
//        return this.getNumeroPage() + 1;
//    }
//
//    public Integer getTotalPageAffiche() {
//        return this.getTotalPage() + 1;
//    }



//    public Integer getNumeroPage()
}
