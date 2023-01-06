package jpabook.jpabook.api;

import jpabook.jpabook.domain.Address;
import jpabook.jpabook.domain.Order;
import jpabook.jpabook.domain.OrderItem;
import jpabook.jpabook.domain.OrderStatus;
import jpabook.jpabook.repository.OrderRepository;
import jpabook.jpabook.repository.OrderSearch;
import jpabook.jpabook.repository.order.query.OrderFlatDto;
import jpabook.jpabook.repository.order.query.OrderQueryDto;
import jpabook.jpabook.repository.order.query.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    //엔티티를 직접 노출하는 방법은 사용하지 말기

    //엔티티를 Dto로 변환해서 하기
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<OrderDto> collect = orders.stream()
                .map(o-> new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }

    //패치 조인으로 최적화
    //단점: 페이징이 불가능하다.(하면 안된다)
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();

        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    //페이징이 가능한 방법
    /**
     * ToOne 관계는 페치 조인해도 페이징에 영향을 주지 않기 때문에 페치 조인으로 쿼리 수를 줄이고
     * 나머지(컬렉션)는 hibernate.default_batch_fetch_size로 최적화(size는 1000개를 넘지 않는게 좋음, 100~1000가 적당)
     */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,//몇번째부터 시작하는지
            @RequestParam(value = "limit", defaultValue = "100") int limit //몇번째까지인지.
    ){
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);//ToOne 관계는 패치 조인으로 가져오기

        // spring.jpa.properties.hibernate.default_batch_fetch_size=100 -> in 쿼리로 한번에 가능(orders 조회 했을때 있는것 만큼)
        //size는 in 쿼리 가능 개수

        List<OrderDto> result = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    //Dto 직접 조회
    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    //Dto 직접 조회- 컬렉션 조회 최적화
    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    //플랫 데이터 최적화
    //중복을 제거 하기 위해서 직접 코드를 구현해야함(OrderQueryDto 형태로 반환하기 위해서)

    /**
     * 나중에 다시 해보기
     */
    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> ordersV6() {
        return orderQueryRepository.findAllByDto_flat();
    }

    @Getter
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            //엔티티 이므로 dto로 변환
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    static class OrderItemDto {
        private String itemName;//상품명
        private int orderPrice;//주문 가격
        private int count;//주문 수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
