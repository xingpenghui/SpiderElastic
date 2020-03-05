package com.elx.spider.entity;

import com.elx.spider.util.DateUtil;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * @program: SpriderEs
 * @description:
 * @author: Feri
 * @create: 2020-03-05 11:01
 */
@Entity
@Table(name = "t_qcjob")
@Data
public class QcJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String jobname;
    private String comname;
    private String workaddr;
    private String salaryrang;
    private Date utime;
    private String jobdetailurl;
    private Date ctime;
    private Integer jid;

    public QcJob() {
    }

    public QcJob(String jobname,int jid, String comname, String workaddr, String salaryrang, String utime, String jobdetailurl) {
        this.jobname = jobname;
        this.jid=jid;
        this.comname = comname;
        this.workaddr = workaddr;
        this.salaryrang = salaryrang;
        this.utime = DateUtil.parseDate(utime);
        this.jobdetailurl = jobdetailurl;
        this.ctime=new Date();
    }
}