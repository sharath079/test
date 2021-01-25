var gProductionMode = false;

function getRazorpayKeyId() 
{
	if(gProductionMode == true)
	{
		return 'rzp_live_RCSU3FTJtjV3CC';
	}
	return 'rzp_test_IETaGOs1uKIc53';
}
function getDomainName() 
{
	if(gProductionMode == true)
	{
		return 'http://www.shashiastro.com';
	}
	return 'http://localhost:8090';
}

function makePaymentUsingRazorpayPaymentGateway(responseObject)
{
	var paymentOrderInfo = responseObject['paymentOrderInfo'];
	var keyId = getRazorpayKeyId();
	var razorpayPaymentOrderId = paymentOrderInfo['razorpayPaymentOrderId'];
	var customerDisplayVal = responseObject['customerDisplayVal'];
	var loginContactNo = '9652090098';
	var loginEmailId = 'abc@gmail.com';
	var address = 'HTS office 160';
	
	$('[name="key_id"]').val(keyId);
	$('[name="order_id"]').val(razorpayPaymentOrderId);
	$('[name="prefill[name]"]').val(customerDisplayVal);
	$('[name="prefill[contact]"]').val(loginContactNo);
	$('[name="prefill[email]"]').val(loginEmailId);
	$('[name="notes[address]"]').val(address);
	$('[name="notes[address]"]').val(address);
	$('[name="callback_url"]').val(getDomainName()+"/razorpay-payment-response"+"?razorpayPaymentOrderId="+razorpayPaymentOrderId);
	$('[name="cancel_url"]').val(getDomainName()+"/razorpay-payment-response"+"?razorpayPaymentOrderId="+razorpayPaymentOrderId);
	$('#razorpayPaymentForm').attr("action", "https://api.razorpay.com/v1/checkout/embedded");
	$('#razorpayPaymentForm').submit();
}

