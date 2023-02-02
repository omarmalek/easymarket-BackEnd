package com.smartweb.market.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.entity.OrderDAO;
import com.smartweb.market.entity.OrderOldDAO;
import com.smartweb.market.entity.OrderRecordDAO;
import com.smartweb.market.entity.OrderRecordOldDAO;
import com.smartweb.market.model.OrderDTO;
import com.smartweb.market.model.OrderRecordDTO;
import com.smartweb.market.model.Ordertobedeleted;
import com.smartweb.market.model.Product;
import com.smartweb.market.model.UserOrderDTO;
import com.smartweb.market.repository.CustomerRepository;
import com.smartweb.market.repository.OrderOldRepository;
import com.smartweb.market.repository.OrderRecordOldRepository;
import com.smartweb.market.repository.OrderRecordRepository;
import com.smartweb.market.repository.OrderRepository;
import com.smartweb.market.repository.OrderRepositorytbedeleted;
import com.smartweb.market.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepositorytbedeleted orderRepositorytobedeleted;// to be deleted

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderRecordRepository orderRecordRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderOldRepository orderOldRepository;

	@Autowired
	OrderRecordOldRepository orderRecordOldRepository;

	public List<Ordertobedeleted> findAll(int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize);
		return orderRepositorytobedeleted.findAll(page).getContent();
	}

	@Override /// to be deleted
	public Ordertobedeleted saveOrder(Ordertobedeleted order) {
		Ordertobedeleted savedOrde = orderRepositorytobedeleted.save(order);
		return savedOrde;
	}

	@Override
	public UserOrderDTO saveUserOrder(UserOrderDTO userOrder) {
		
		long customerId = 0;
		Customer presentCustomer = customerRepository.findByPhoneNumber(userOrder.getCustomerPhone());
		// if new customer save customer ... get customer id

		if (presentCustomer == null) {
			Customer newCustomer = new Customer();
			newCustomer.setPhoneNumber(userOrder.getCustomerPhone());
			newCustomer.setName(userOrder.getCustomerName());
			newCustomer.setAddress(userOrder.getCustomerAddress());
			Customer savedCustomer = customerRepository.save(newCustomer);
			customerId = savedCustomer.getId();
		} else {
			// if old customer update customer info
			//presentCustomer.setName(userOrder.getCustomerName());//don't change name : customer can't change customer name
			presentCustomer.setAddress(userOrder.getCustomerAddress());
			Customer savedCustomer = customerRepository.save(presentCustomer);
			customerId = savedCustomer.getId();
		}
		// save order
		OrderDAO databaseOrder = new OrderDAO();
		databaseOrder.setCustomerId(customerId);

		databaseOrder.setDelivaryCharge(3);// this need to be evaluated.
		databaseOrder.setCartTotal(userOrder.getCartTotal());

		databaseOrder.setPaymentType(userOrder.getPaymentType());
		databaseOrder.setDelivaryServiceType(userOrder.getDelivaryServiceType());

		OrderDAO dbOrder = orderRepository.save(databaseOrder);
		long dbOrderId = dbOrder.getId();

		// save order records
		List<OrderRecordDAO> orderCart = userOrder.getOrderCart();
		for (OrderRecordDAO record : orderCart) {
			record.setOrderId(dbOrderId);
			record.setProductId(record.getProductId());
			record.setProductAmount(record.getProductAmount());

			orderRecordRepository.save(record);
		}

		// userOrder.setCustomerId(savedCustomer.getId());

		return userOrder;
	}

	@Override
	public List<OrderDTO> getControlOrders(int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize,Sort.by("id").descending());
		List<OrderDAO> list = orderRepository.findAll(page).getContent();
		return convertToDTO(list);

	}

	@Override
	public List<OrderDTO> getSetterOrders(int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize,Sort.by("id").descending());
		List<OrderDAO> list = orderRepository.getSetterOrders(page).getContent();
		return convertToDTO(list);

	}

	@Override
	public OrderDTO updateOrder(OrderDTO orderDTO) {
		
		if (orderDTO.isDelivered() || orderDTO.isCancelled() || orderDTO.isRejected()) {
			OrderOldDAO orderOldDAO = new OrderOldDAO();
			orderOldDAO.setId(orderDTO.getId());  //take the old id >> this make it move the order not create new order
			orderOldDAO.setCustomerId(orderDTO.getCustomerId());
			orderOldDAO.setOrderSetterId(orderDTO.getOrderSetterId());
			orderOldDAO.setDeliveryManId(orderDTO.getDeliveryManId());
			orderOldDAO.setDelivaryCharge(orderDTO.getDelivaryCharge());
			orderOldDAO.setCartTotal(orderDTO.getCartTotal());
			orderOldDAO.setDate(orderDTO.getDate());
			orderOldDAO.setPaymentType(orderDTO.getPaymentType());
			orderOldDAO.setDelivaryServiceType(orderDTO.getDelivaryServiceType());
			orderOldDAO.setPacked(orderDTO.isPacked());
			orderOldDAO.setSentDelivery(orderDTO.isSentDelivery());
			orderOldDAO.setDelivered(orderDTO.isDelivered());
			orderOldDAO.setPaid(orderDTO.isPaid());
			orderOldDAO.setCancelled(orderDTO.isCancelled());
			orderOldDAO.setRejected(orderDTO.isRejected());
			orderOldDAO.setCustomerEvaluation(orderDTO.getCustomerEvaluation());
			orderOldDAO.setControlNotes(orderDTO.getControlNotes());

			orderOldRepository.save(orderOldDAO);
			//move the cart
			
			
			List<OrderRecordDTO> sentRecordList = orderDTO.getOrderCart();
			System.out.println("sentRecordList" + sentRecordList);
			List<OrderRecordOldDAO> newlistOfOldDAORecords = new ArrayList<>();
			for (OrderRecordDTO singlRecord: sentRecordList) {
				OrderRecordOldDAO recordToSave = new OrderRecordOldDAO(); 
				//recordToSave.setId(singlRecord.getId());
				recordToSave.setOrderId(singlRecord.getOrderId());
				recordToSave.setProductId(singlRecord.getProductId());
				recordToSave.setProductAmount(singlRecord.getProductAmount());
				orderRecordOldRepository.save(recordToSave);
				//newlistOfOldDAORecords.add(recordToSave);
			}
			//orderRecordOldRepository.saveAll(newlistOfOldDAORecords);//this did'nt work i don't why???
			orderRepository.deleteById(orderDTO.getId());
		} else {
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.setId(orderDTO.getId()); // update the order not create new order
			orderDAO.setCustomerId(orderDTO.getCustomerId());
			orderDAO.setOrderSetterId(orderDTO.getOrderSetterId());
			orderDAO.setDeliveryManId(orderDTO.getDeliveryManId());
			orderDAO.setDelivaryCharge(orderDTO.getDelivaryCharge());
			orderDAO.setCartTotal(orderDTO.getCartTotal());
			orderDAO.setDate(orderDTO.getDate());
			orderDAO.setPaymentType(orderDTO.getPaymentType());
			orderDAO.setDelivaryServiceType(orderDTO.getDelivaryServiceType());
			orderDAO.setPacked(orderDTO.isPacked());
			orderDAO.setSentDelivery(orderDTO.isSentDelivery());
			orderDAO.setDelivered(orderDTO.isDelivered());
			orderDAO.setPaid(orderDTO.isPaid());
			orderDAO.setCancelled(orderDTO.isCancelled());
			orderDAO.setRejected(orderDTO.isRejected());
			orderDAO.setCustomerEvaluation(orderDTO.getCustomerEvaluation());
			orderDAO.setControlNotes(orderDTO.getControlNotes());
			orderRepository.save(orderDAO);
		}

		return orderDTO;
	}

	@Override
	public List<OrderDTO> getOldOrders(int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize,Sort.by("id").descending());
		
		List<OrderOldDAO> listOfOldOrders = orderOldRepository.findAll(page).getContent();

		List<OrderDTO> listofOrderDTO = new ArrayList<>();

		for (OrderOldDAO databaseOrder : listOfOldOrders) {
			OrderDTO orderDTO = new OrderDTO();

			orderDTO.setId(databaseOrder.getId());
			orderDTO.setCustomerId(databaseOrder.getCustomerId());
			orderDTO.setOrderSetterId(databaseOrder.getOrderSetterId());
			orderDTO.setDeliveryManId(databaseOrder.getDeliveryManId());// need change
			orderDTO.setDelivaryCharge(databaseOrder.getDelivaryCharge());
			orderDTO.setCartTotal(databaseOrder.getCartTotal());
			orderDTO.setDate(databaseOrder.getDate());
			orderDTO.setPaymentType(databaseOrder.getPaymentType());
			orderDTO.setDelivaryServiceType(databaseOrder.getDelivaryServiceType());
			orderDTO.setPacked(databaseOrder.isPacked());
			orderDTO.setSentDelivery(databaseOrder.isSentDelivery());
			orderDTO.setDelivered(databaseOrder.isDelivered());
			orderDTO.setPaid(databaseOrder.isPaid());
			orderDTO.setCancelled(databaseOrder.isCancelled());
			orderDTO.setRejected(databaseOrder.isRejected());
			orderDTO.setCustomerEvaluation(databaseOrder.getCustomerEvaluation());
			orderDTO.setControlNotes(databaseOrder.getControlNotes());

			Customer customer = customerRepository.findById(databaseOrder.getCustomerId()).get();
			orderDTO.setCustomerName(customer.getName());
			orderDTO.setCustomerPhone(customer.getPhoneNumber());
			orderDTO.setCustomerAddress(customer.getAddress());

			List<OrderRecordOldDAO> listOfDAORecords = orderRecordOldRepository.findByOrderId(databaseOrder.getId());
			
			List<OrderRecordDTO> listOfDTORecords = new ArrayList<>();

			for (OrderRecordOldDAO databaseRecord : listOfDAORecords) {
				OrderRecordDTO recordDTO = new OrderRecordDTO();

				//get product amount
				recordDTO.setProductAmount(databaseRecord.getProductAmount());
				
				// fetch product name and price and other info
				Product product = productRepository.findById(databaseRecord.getProductId()).get();
				recordDTO.setProductName(product.getName());
				recordDTO.setPackType(product.getPackType());
				recordDTO.setUnitPrice(product.getUnitPrice());

				listOfDTORecords.add(recordDTO);
			}

			orderDTO.setOrderCart(listOfDTORecords);
			
			listofOrderDTO.add(orderDTO);
		}

		return listofOrderDTO;

	}

	public List<OrderDTO> convertToDTO(List<OrderDAO> source) {
		List<OrderDTO> list = new ArrayList<>();
		for (OrderDAO databaseOrder : source) {
			OrderDTO orderDTO = new OrderDTO();

			orderDTO.setId(databaseOrder.getId());
			orderDTO.setCustomerId(databaseOrder.getCustomerId());
			orderDTO.setOrderSetterId(databaseOrder.getOrderSetterId());
			orderDTO.setDeliveryManId(databaseOrder.getDeliveryManId());// need change
			orderDTO.setDelivaryCharge(databaseOrder.getDelivaryCharge());
			orderDTO.setCartTotal(databaseOrder.getCartTotal());
			orderDTO.setDate(databaseOrder.getDate());
			orderDTO.setPaymentType(databaseOrder.getPaymentType());
			orderDTO.setDelivaryServiceType(databaseOrder.getDelivaryServiceType());
			orderDTO.setPacked(databaseOrder.isPacked());
			orderDTO.setSentDelivery(databaseOrder.isSentDelivery());
			orderDTO.setDelivered(databaseOrder.isDelivered());
			orderDTO.setPaid(databaseOrder.isPaid());
			orderDTO.setCancelled(databaseOrder.isCancelled());
			orderDTO.setRejected(databaseOrder.isRejected());
			orderDTO.setCustomerEvaluation(databaseOrder.getCustomerEvaluation());
			orderDTO.setControlNotes(databaseOrder.getControlNotes());

			Customer customer = customerRepository.findById(databaseOrder.getCustomerId()).get();
			orderDTO.setCustomerName(customer.getName());
			orderDTO.setCustomerPhone(customer.getPhoneNumber());
			orderDTO.setCustomerAddress(customer.getAddress());

			List<OrderRecordDAO> listOfDAORecords = orderRecordRepository.findByOrderId(databaseOrder.getId());
			List<OrderRecordDTO> listOfDTORecords = new ArrayList<>();

			for (OrderRecordDAO databaseRecord : listOfDAORecords) {
				OrderRecordDTO recordDTO = new OrderRecordDTO();

				recordDTO.setId(databaseRecord.getId());
				recordDTO.setOrderId(databaseRecord.getOrderId());// no need
				recordDTO.setProductId(databaseRecord.getProductId());
				recordDTO.setProductAmount(databaseRecord.getProductAmount());
				// fetch product name
				Product product = productRepository.findById(databaseRecord.getProductId()).get();
				if (product != null) {
					recordDTO.setProductName(product.getName());
					recordDTO.setPackType(product.getPackType());
					recordDTO.setUnitPrice(product.getUnitPrice());
				}
				listOfDTORecords.add(recordDTO);
			}
			
			orderDTO.setOrderCart(listOfDTORecords);
			list.add(orderDTO);
		}
		return list;
	}

	@Override
	public List<OrderDTO> getCustomerOrders(long customerId, int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize,Sort.by("id").descending());

		List<OrderDAO> res = orderRepository.getCustomerOrders(customerId, page).getContent();
		return convertToDTO(res);
	}

	@Override
	public List<OrderDTO> getCustomerOldOrders(long customerId, int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize);

		List<OrderOldDAO> listOfOldOrders = orderOldRepository.findAllByCustomerId(customerId, page).getContent();

		  List<OrderDTO> listofOrderDTO = new ArrayList<>();
		  
		  for (OrderOldDAO databaseOrder : listOfOldOrders) 
		  { OrderDTO orderDTO = new OrderDTO();
		  
		  orderDTO.setId(databaseOrder.getId());
		  orderDTO.setCustomerId(customerId);
		  orderDTO.setOrderSetterId(databaseOrder.getOrderSetterId());
		  orderDTO.setDeliveryManId(databaseOrder.getDeliveryManId());// need change
		  orderDTO.setDelivaryCharge(databaseOrder.getDelivaryCharge());
		  orderDTO.setCartTotal(databaseOrder.getCartTotal());
		  orderDTO.setDate(databaseOrder.getDate());
		  orderDTO.setPaymentType(databaseOrder.getPaymentType());
		  orderDTO.setDelivaryServiceType(databaseOrder.getDelivaryServiceType());
		  orderDTO.setPacked(databaseOrder.isPacked());
		  orderDTO.setSentDelivery(databaseOrder.isSentDelivery());
		  orderDTO.setDelivered(databaseOrder.isDelivered());
		  orderDTO.setPaid(databaseOrder.isPaid());
		  orderDTO.setCancelled(databaseOrder.isCancelled());
		  orderDTO.setRejected(databaseOrder.isRejected());
		  orderDTO.setCustomerEvaluation(databaseOrder.getCustomerEvaluation());
		  orderDTO.setControlNotes(databaseOrder.getControlNotes());
		  
		  //Customer info
		  Customer customer = customerRepository.findById(customerId).get();
		  orderDTO.setCustomerName(customer.getName());
		  orderDTO.setCustomerPhone(customer.getPhoneNumber());
		  orderDTO.setCustomerAddress(customer.getAddress());
		  
		  //get cart for this order
		  List<OrderRecordOldDAO> listOfDAORecords = orderRecordOldRepository.findByOrderId(databaseOrder.getId());
		  List<OrderRecordDTO> listOfDTORecords = new ArrayList<>();
		  
		  
		  for (OrderRecordOldDAO databaseRecord : listOfDAORecords) { 
			  OrderRecordDTO recordDTO = new OrderRecordDTO();
		  
		  recordDTO.setId(databaseRecord.getId());
		  recordDTO.setOrderId(databaseRecord.getOrderId());// no need
		  recordDTO.setProductId(databaseRecord.getProductId());
		  recordDTO.setProductAmount(databaseRecord.getProductAmount()); // fetch product name
		  Product product = productRepository.findById(databaseRecord.getProductId()).get();
		  recordDTO.setProductName(product.getName());
		  recordDTO.setPackType(product.getPackType());
		  recordDTO.setUnitPrice(product.getUnitPrice()); 
		  listOfDTORecords.add(recordDTO);
		  
		  }
		  
		  
		  orderDTO.setOrderCart(listOfDTORecords); 
		  
		  listofOrderDTO.add(orderDTO); }
		 

		return listofOrderDTO;
	}

	@Override
	public List<OrderDTO> getDeliveryOrders(int pageIndex, int pageSize) {
		PageRequest page = PageRequest.of(pageIndex, pageSize,Sort.by("id").descending());
		List<OrderDAO> list = orderRepository.getDeliveryOrders(page).getContent(); //that is enough 
		return convertToDTO(list);
	};

}
