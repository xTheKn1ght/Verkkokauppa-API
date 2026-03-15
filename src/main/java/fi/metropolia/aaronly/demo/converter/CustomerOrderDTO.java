package fi.metropolia.aaronly.demo.converter;

public class CustomerOrderDTO {
    private Integer customerId;
    private Integer shippingAddressId;
    private String status;

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getShippingAddressId() { return shippingAddressId; }
    public void setShippingAddressId(Integer shippingAddressId) { this.shippingAddressId = shippingAddressId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}