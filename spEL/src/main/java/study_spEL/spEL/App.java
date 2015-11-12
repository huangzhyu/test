package study_spEL.spEL;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		String randomPhrase = parser.parseExpression(
				"random number is #{T(java.lang.Math).random()}",
				new TemplateParserContext()).getValue(String.class);
		
		System.out.println(randomPhrase);
		
		
		PerformanceValue pv = PerformanceValue.newInstance().customerId(100L);
		
		
		String str=" <csId>#{customerId}</csId> <csIdPub>CUST#{customerId}</csIdPub>";
		
		 EvaluationContext context = new StandardEvaluationContext(pv);
		
		 str = parser.parseExpression(str,new TemplateParserContext()).getValue(context,String.class);
		 
		 System.out.println(str);
		
		 
		
	}
}


class PerformanceValue {

    private Long customerId;
    private Long contractId;
    private String msisdn;
    private Long rpcode;
    private Long sncode;
    private String coIdPub;
    private Long ctId;
    private Long documentId;

    public static PerformanceValue newInstance() {
        return new PerformanceValue();
    }

    public PerformanceValue customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public PerformanceValue contractId(Long contractId) {
        this.contractId = contractId;
        return this;
    }

    public PerformanceValue msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    public PerformanceValue rpcode(Long rpcode) {
        this.rpcode = rpcode;
        return this;
    }

    public PerformanceValue sncode(Long sncode) {
        this.sncode = sncode;
        return this;
    }

    public PerformanceValue coIdPub(String coIdPub) {
        this.coIdPub = coIdPub;
        return this;
    }

    public PerformanceValue ctId(Long ctId) {
        this.ctId = ctId;
        return this;
    }
    
    public PerformanceValue documentId(Long documentId) {
        this.documentId = documentId;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getRpcode() {
        return rpcode;
    }

    public void setRpcode(Long rpcode) {
        this.rpcode = rpcode;
    }

    public Long getSncode() {
        return sncode;
    }

    public void setSncode(Long sncode) {
        this.sncode = sncode;
    }

    public String getCoIdPub() {
        return coIdPub;
    }

    public void setCoIdPub(String coIdPub) {
        this.coIdPub = coIdPub;
    }

    public Long getCtId() {
        return ctId;
    }

    public void setCtId(Long ctId) {
        this.ctId = ctId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("|customerId:").append(customerId);
        sb.append("|contractId:").append(contractId);
        sb.append("|msisdn:").append(msisdn);
        sb.append("|rpcode:").append(rpcode);
        sb.append("|sncode:").append(sncode);
        sb.append("|coIdPub:").append(coIdPub);
        sb.append("|ctId:").append(ctId);
        sb.append("|documentId:").append(documentId);
        return sb.toString();
    }

}
