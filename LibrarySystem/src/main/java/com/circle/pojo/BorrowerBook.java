package com.circle.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author circle
 * @since 2022-06-20
 */
@Getter
@Setter
@TableName("borrower_book")
@ApiModel(value = "BorrowerBook对象", description = "")
public class BorrowerBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bbId", type = IdType.AUTO)
    private Integer bbId;

    @TableField("borrowerId")
    private Integer borrowerId;

    @TableField("bookId")
    private Integer bookId;

    @TableField("borrowDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date borrowDate;

    @TableField("returnDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date returnDate;

    @TableField(exist = false)
    private Borrower borrower;

    @TableField(exist = false)
    private Book book;



}
