package com.vw.runtime;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import com.google.gson.JsonObject;
@SuppressWarnings("unused")
public abstract class BusinessRulesRepositry extends VWDefaultController
{
	VWResponseBean vwResponseBean = new VWResponseBean();
	VWMastersBean vwMastersBean = new VWMastersBean();
	public BusinessRulesRepositry(Session session)
	{
		super(session);
	}
	public BusinessRulesRepositry()
	{
		super();
	}
	protected String executeRuleTest(String sString)
	{
		if (sString.equalsIgnoreCase("HELLO"))
		{
			addCustomResponse("Hello not allowed");
		}
		return sString;
	}
	public void executeRuleC03(String CcyCode, BigDecimal swiftAmount)
	{
		// needed as per template base
	}
	protected ArrayList executeRuleCalculateItemTotal(BigDecimal sTagInvoiceQuantity, BigDecimal sTagUnitPrice, BigDecimal sTagSubTotal)
	{
		sTagSubTotal = sTagInvoiceQuantity.multiply(sTagUnitPrice);
		List returnArray = new ArrayList();
		returnArray.add(sTagSubTotal);
		return (ArrayList) returnArray;
	}
	protected ArrayList executeRuleSUM(BigDecimal sTagInvoicequantity, BigDecimal sTagUnitdiscountamount, BigDecimal sTagSubtotal, BigDecimal sTagSubtotalwithtaxes)
	{
		// TODO Auto-generated method stub
		sTagSubtotal = sTagInvoicequantity.multiply(sTagUnitdiscountamount);
		sTagSubtotalwithtaxes = new BigDecimal("2000.12");
		List returnArray = new ArrayList();
		returnArray.add(sTagSubtotal);
		returnArray.add(sTagSubtotalwithtaxes);
		return (ArrayList) returnArray;
	}
	protected ArrayList executeRuleCalculateLineItemPriceInfo(BigDecimal sTagItemQuantity, BigDecimal sTagUnitPriceWithoutDiscountInput, String sTagApplyDiscount, String sTagDiscountType, BigDecimal sTagDiscountPercentage, BigDecimal sTagUnitDiscountAmount, BigDecimal sTagCgstTaxRate, BigDecimal sTagSgstTaxRate, BigDecimal sTagIgstTaxRate, BigDecimal sTagLineItemUnitDiscountAmount, BigDecimal sTagUnitPrice, BigDecimal sTagLineItemTotalDiscountAmount, BigDecimal sTagUnitPriceBeforeTaxes, BigDecimal sTagUnitPriceAfterTaxes, BigDecimal sTagCgstTaxAmount, BigDecimal sTagSgstTaxAmount, BigDecimal sTagIgstTaxAmount, BigDecimal sTagSubTotalBeforeTaxes,BigDecimal sTagSubTotal, BigDecimal sTagSubTotalAfterTaxes)
	{
		BigDecimal unitPriceWithoutDiscountInput = sTagUnitPriceWithoutDiscountInput;
		BigDecimal lineItemUnitDiscountAmount = new BigDecimal(0);
		if (sTagApplyDiscount.equals("YES"))
		{
			if (sTagDiscountType.equals("PERCENTAGE"))
			{
				lineItemUnitDiscountAmount = unitPriceWithoutDiscountInput.multiply(sTagDiscountPercentage).divide(new BigDecimal(100));
			}
			else
			{
				lineItemUnitDiscountAmount = sTagUnitDiscountAmount;
			}
		}
		sTagUnitPrice = unitPriceWithoutDiscountInput.subtract(lineItemUnitDiscountAmount);
		sTagLineItemUnitDiscountAmount = lineItemUnitDiscountAmount;
		sTagUnitPriceBeforeTaxes = sTagUnitPrice;
		sTagUnitPriceAfterTaxes = sTagUnitPrice;
		if (sTagCgstTaxRate == null)
		{
			sTagCgstTaxRate = new BigDecimal(0);
		}
		if (sTagSgstTaxRate == null)
		{
			sTagSgstTaxRate = new BigDecimal(0);
		}
		if (sTagIgstTaxRate == null)
		{
			sTagIgstTaxRate = new BigDecimal(0);
		}
		BigDecimal sTagUnitCgstAmount = sTagUnitPrice.multiply(sTagCgstTaxRate).divide(new BigDecimal(100));
		BigDecimal sTagUnitSgstAmount = sTagUnitPrice.multiply(sTagSgstTaxRate).divide(new BigDecimal(100));
		BigDecimal sTagUnitIgstAmount = sTagUnitPrice.multiply(sTagIgstTaxRate).divide(new BigDecimal(100));
		sTagUnitPriceAfterTaxes = sTagUnitPriceAfterTaxes.add(sTagUnitCgstAmount).add(sTagUnitSgstAmount).add(sTagUnitIgstAmount);
		sTagLineItemTotalDiscountAmount = sTagLineItemUnitDiscountAmount.multiply(sTagItemQuantity);
		sTagSubTotal = sTagItemQuantity.multiply(sTagUnitPrice);
		sTagSubTotalBeforeTaxes = sTagSubTotal;
		sTagCgstTaxAmount = sTagSubTotal.multiply(sTagCgstTaxRate).divide(new BigDecimal(100));
		sTagSgstTaxAmount = sTagSubTotal.multiply(sTagSgstTaxRate).divide(new BigDecimal(100));
		sTagIgstTaxAmount = sTagSubTotal.multiply(sTagIgstTaxRate).divide(new BigDecimal(100));
		sTagSubTotalAfterTaxes = sTagSubTotal.add(sTagCgstTaxAmount).add(sTagSgstTaxAmount).add(sTagIgstTaxAmount);
		List returnArray = new ArrayList();
		returnArray.add(sTagLineItemUnitDiscountAmount);
		returnArray.add(sTagUnitPrice);
		returnArray.add(sTagLineItemTotalDiscountAmount);
		returnArray.add(sTagUnitPriceBeforeTaxes);
		returnArray.add(sTagUnitPriceAfterTaxes);
		returnArray.add(sTagCgstTaxAmount);
		returnArray.add(sTagSgstTaxAmount);
		returnArray.add(sTagIgstTaxAmount);
		returnArray.add(sTagSubTotalBeforeTaxes);
		returnArray.add(sTagSubTotal);
		returnArray.add(sTagSubTotalAfterTaxes);
		return (ArrayList) returnArray;
	}
	protected ArrayList executeRuleCalculateLineItemPriceInfoWithoutDiscount(BigDecimal sTagItemQuantity, BigDecimal sTagUnitPrice, BigDecimal sTagCgstTaxRate, BigDecimal sTagSgstTaxRate, BigDecimal sTagIgstTaxRate, BigDecimal sTagUnitPriceBeforeTaxes, BigDecimal sTagUnitPriceAfterTaxes, BigDecimal sTagCgstTaxAmount, BigDecimal sTagSgstTaxAmount, BigDecimal sTagIgstTaxAmount, BigDecimal sTagSubTotalBeforeTaxes, BigDecimal sTagSubTotal, BigDecimal sTagSubTotalAfterTaxes)
	{
		sTagUnitPriceBeforeTaxes = sTagUnitPrice;
		sTagUnitPriceAfterTaxes = sTagUnitPrice;
		if (sTagCgstTaxRate == null)
		{
			sTagCgstTaxRate = new BigDecimal(0);
		}
		if (sTagSgstTaxRate == null)
		{
			sTagSgstTaxRate = new BigDecimal(0);
		}
		if (sTagIgstTaxRate == null)
		{
			sTagIgstTaxRate = new BigDecimal(0);
		}
		BigDecimal sTagUnitCgstAmount = sTagUnitPrice.multiply(sTagCgstTaxRate).divide(new BigDecimal(100));
		BigDecimal sTagUnitSgstAmount = sTagUnitPrice.multiply(sTagSgstTaxRate).divide(new BigDecimal(100));
		BigDecimal sTagUnitIgstAmount = sTagUnitPrice.multiply(sTagIgstTaxRate).divide(new BigDecimal(100));
		sTagUnitPriceAfterTaxes = sTagUnitPriceAfterTaxes.add(sTagUnitCgstAmount).add(sTagUnitSgstAmount).add(sTagUnitIgstAmount);
		sTagSubTotal = sTagItemQuantity.multiply(sTagUnitPrice);
		sTagSubTotalBeforeTaxes = sTagSubTotal;
		sTagCgstTaxAmount = sTagSubTotal.multiply(sTagCgstTaxRate).divide(new BigDecimal(100));
		sTagSgstTaxAmount = sTagSubTotal.multiply(sTagSgstTaxRate).divide(new BigDecimal(100));
		sTagIgstTaxAmount = sTagSubTotal.multiply(sTagIgstTaxRate).divide(new BigDecimal(100));
		sTagSubTotalAfterTaxes = sTagSubTotal.add(sTagCgstTaxAmount).add(sTagSgstTaxAmount).add(sTagIgstTaxAmount);
		List returnArray = new ArrayList();
		returnArray.add(sTagUnitPriceBeforeTaxes);
		returnArray.add(sTagUnitPriceAfterTaxes);
		returnArray.add(sTagCgstTaxAmount);
		returnArray.add(sTagSgstTaxAmount);
		returnArray.add(sTagIgstTaxAmount);
		returnArray.add(sTagSubTotalBeforeTaxes);
		returnArray.add(sTagSubTotal);
		returnArray.add(sTagSubTotalAfterTaxes);
		return (ArrayList) returnArray;
	}
	protected String getCurrentLcNo()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String lcNo = vwSessionObject.getParamValue("LC_NUMBER");
		return lcNo;
	}
	public void drawChartMonth(OutputStream out, Object data) throws IOException
	{
		System.out.println("Executed drawChartMonth");
		JFreeChart barChart = ChartFactory.createBarChart("", "Transactions - Month Wise", "No.", getDataMonth(), PlotOrientation.VERTICAL, false, true, false);
		BufferedImage bufferedImage = barChart.createBufferedImage(500, 550);
		ImageIO.write(bufferedImage, "gif", out);
	}
	public void drawChartStatus(OutputStream out, Object data) throws IOException
	{
		System.out.println("Executed drawChartStatus");
		JFreeChart barChart = ChartFactory.createBarChart("", "Transactions - Status Wise", "No.", getDataStatus(), PlotOrientation.VERTICAL, false, true, false);
		BufferedImage bufferedImage = barChart.createBufferedImage(500, 550);
		ImageIO.write(bufferedImage, "gif", out);
	}
	public Date getTimeStamp()
	{
		return new Date();
	}
	public DefaultCategoryDataset getDataMonth() throws IOException
	{
		String sEntityName = getManagedBeanName();
		sEntityName = StringUtils.remove(sEntityName, "Bean");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sSql = "select CONCAT(DATE_FORMAT(vwLastModifiedDate,'%b'),DATE_FORMAT(vwLastModifiedDate,'%Y')) AS MYDATE, vwTxnStatus as TXN_STATUS, count(*) as  NO_OF_LCS from " + sEntityName + " group by CONCAT(DATE_FORMAT(vwLastModifiedDate,'%b'),DATE_FORMAT(vwLastModifiedDate,'%Y')), vwTxnStatus";
		List<?> list = getDBQuery(sSql);
		Iterator<?> it = list.iterator();
		while (it.hasNext())
		{
			Object grprow[] = (Object[]) it.next();
			String sMonth = (String) grprow[0];
			String sStatus = (String) grprow[1];
			Long noTxns = (Long) grprow[2];
			dataset.addValue(noTxns, sMonth, sMonth);
			// dataset.addValue(noTxns, "Status", sStatus);
		}
		return dataset;
	}
	public DefaultCategoryDataset getDataStatus() throws IOException
	{
		String sEntityName = getManagedBeanName();
		sEntityName = StringUtils.remove(sEntityName, "Bean");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sSql = "select vwTxnStatus as TXN_STATUS, count(*) as  NO_OF_LCS from " + sEntityName + " group by vwTxnStatus";
		List<?> list = getDBQuery(sSql);
		Iterator<?> it = list.iterator();
		while (it.hasNext())
		{
			Object grprow[] = (Object[]) it.next();
			String sStatus = (String) grprow[0];
			Long noTxns = (Long) grprow[1];
			dataset.addValue(noTxns, sStatus, sStatus);
		}
		return dataset;
	}
	protected void setCurrentLcNo(String sLCNo)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setParamValue("LC_NUMBER", sLCNo);
	}
	protected void setGroupName(String sGroupName)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setParamValue("GROUP_NAME", sGroupName);
	}
	public String getGroupName()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String sGroupName = vwSessionObject.getParamValue("GROUP_NAME");
		return sGroupName;
	}
	protected String getCurrentMessageQueueId()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String sMessageQueueId = vwSessionObject.getParamValue("MESSAGEQUEUE_ID");
		return sMessageQueueId;
	}
	protected void setCurrentMessageQueueId(String sMessageQueueId)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setParamValue("MESSAGEQUEUE_ID", sMessageQueueId);
	}
	protected BigDecimal convertSwiftAmtToBigDecimal(String str)
	{
		if (isExists(str))
		{
			str = removeUnwanted(str);
			str = StringUtils.replace(str, ",", ".");
			int dotIndex = StringUtils.lastIndexOf(str, ".");
		}
		return new BigDecimal(str);
	}
	protected String generateMGUID()
	{
		String MG_MGID_PREFIX = "VWMG";
		String MG_MGID_FORMAT = "ddMMYYYYHHmmssmm";
		SimpleDateFormat sdfDate = new SimpleDateFormat(MG_MGID_FORMAT);
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return MG_MGID_PREFIX + strDate;
	}
	protected String removeUnwanted(String str)
	{
		String sLastTwoChars = StringUtils.substring(str, str.length() - 1, str.length());
		if (StringUtils.contains(sLastTwoChars, NEW_LINE_CHAR))
		{
			str = StringUtils.substring(str, 0, str.length() - 1);
		}
		str = StringUtils.remove(str, "&#xd;");
		return str;
	}
	protected String handleErrors(String sErrorCode, String sTag, String str)
	{
		String[] sParams = new String[2];
		sParams[0] = sTag;
		sParams[1] = str;
		String sErrorDesc = getErrorMessage(sErrorCode, sParams);
		addApplicationResponse("MG-SWIFT-HELPER", sErrorCode, sParams);
		return sErrorDesc;
	}
	protected String getErrorMessage(String sErrorCode, String[] sParams)
	{
		return vwResponseBean.getMessage(isActionFromUI(), sErrorCode, sParams);
	}
	protected String getErrorMessage(String sErrorCode)
	{
		return vwResponseBean.getMessage(isActionFromUI(), sErrorCode);
	}
	// End Utility Methods
	abstract public void doValidations();
	abstract protected void doEnrichValues(Boolean doBusinessRules, JsonObject paramsInfoObj);
	abstract protected void doAfterEnrichValues();
	abstract protected void doEnrichSystemValues(String sAction, String sNextStatus);
	abstract protected HashMap<?, ?> getSearchParams();
	abstract public String getLabel(String sResponseField);
	abstract protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex);
	// Trade Helper Methods END
}
