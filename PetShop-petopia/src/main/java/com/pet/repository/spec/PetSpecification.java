package com.pet.repository.spec;

import com.pet.entity.Pet;
import com.pet.entity.Review;
import com.pet.enums.SearchOperation;
import com.pet.modal.request.PetSearchRequestDTO;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import static com.pet.enums.SearchOperation.GREATER_THAN_OR_EQUAL;

public class PetSpecification implements Specification<Pet> {
    private final PetSearchRequestDTO requestDTO;

    public PetSpecification(PetSearchRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }
    
    private static class SearchCriteriaConfig {
        private String field;
        private SearchOperation operation;
        private String requestField;
        private String joinEntity;
        private String joinField;

        public SearchCriteriaConfig(String field, SearchOperation operation, String requestField) {
            this(field, operation, requestField, null, null);
        }

        public SearchCriteriaConfig(String field, SearchOperation operation, String requestField, String joinEntity) {
            this(field, operation, requestField, joinEntity, null);
        }

        public SearchCriteriaConfig(String field, SearchOperation operation, String requestField, String joinEntity, String joinField) {
            this.field = field;
            this.operation = operation;
            this.requestField = requestField;
            this.joinEntity = joinEntity;
            this.joinField = joinField;
        }

        public String getField() { return field; }
        public SearchOperation getOperation() { return operation; }
        public String getRequestField() { return requestField; }
        public String getJoinEntity() { return joinEntity; }
        public String getJoinField() { return joinField; }
    }

    private static final SearchCriteriaConfig[] basicConfig = {
            new SearchCriteriaConfig("name", SearchOperation.LIKE, "name"),
            new SearchCriteriaConfig("categoryId", SearchOperation.EQUAL, "categoryId", "category"),
            new SearchCriteriaConfig("age", GREATER_THAN_OR_EQUAL, "minAge"),
            new SearchCriteriaConfig("age", SearchOperation.LESS_THAN_OR_EQUAL, "maxAge"),
            new SearchCriteriaConfig("price", GREATER_THAN_OR_EQUAL, "minPrice"),
            new SearchCriteriaConfig("price", SearchOperation.LESS_THAN_OR_EQUAL, "maxPrice"),
            new SearchCriteriaConfig("healthStatus", SearchOperation.EQUAL, "healthStatus"),
            new SearchCriteriaConfig("height", GREATER_THAN_OR_EQUAL, "minHeight"),
            new SearchCriteriaConfig("height", SearchOperation.LESS_THAN_OR_EQUAL, "maxHeight"),
            new SearchCriteriaConfig("weight", GREATER_THAN_OR_EQUAL, "minWeight"),
            new SearchCriteriaConfig("weight", SearchOperation.LESS_THAN_OR_EQUAL, "maxWeight"),
            new SearchCriteriaConfig("rating", GREATER_THAN_OR_EQUAL, "minRating", "reviews", "rating"),
            new SearchCriteriaConfig("discountPrice", SearchOperation.LESS_THAN_OR_EQUAL, "onSale", null, "discountPrice"),
            new SearchCriteriaConfig("color", SearchOperation.EQUAL, "color"),
            new SearchCriteriaConfig("furType", SearchOperation.EQUAL, "furType")
    };

    @Override
    public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        predicate = buildBasicPredicates(predicate, root, cb);
        predicate = buildRatingPredicate(predicate, root, query, cb);
        predicate = buildOnSalePredicate(predicate, root, cb);
        return predicate;
    }

    private Predicate buildBasicPredicates(Predicate predicate, Root<Pet> root, CriteriaBuilder cb) {
        for (SearchCriteriaConfig config : basicConfig) {
            Object value = getFieldValue(config.getRequestField());
            if (value != null && !value.toString().trim().isEmpty()) {
                Path<?> path = root;
                if (config.getJoinEntity() != null) {
                    for (String join : config.getJoinEntity().split("\\.")) {
                        path = root.join(join, JoinType.LEFT);
                    }
                }
                path = config.getJoinField() != null ? path.get(config.getJoinField()) : path.get(config.getField());

                if (!"minRating".equals(config.getRequestField()) && !"onSale".equals(config.getRequestField())) {
                    switch (config.getOperation()) {
                        case EQUAL -> predicate = cb.and(predicate, cb.equal(path, value));
                        case LIKE -> predicate = cb.and(predicate, cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%"));
                        case GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL -> {
                            if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                                Number numValue = (Number) value;
                                if (Double.class.isAssignableFrom(path.getJavaType()) || double.class.isAssignableFrom(path.getJavaType())) {
                                    predicate = cb.and(predicate, (config.getOperation() == GREATER_THAN_OR_EQUAL)
                                            ? cb.greaterThanOrEqualTo(path.as(Double.class), numValue.doubleValue())
                                            : cb.lessThanOrEqualTo(path.as(Double.class), numValue.doubleValue()));
                                } else if (Integer.class.isAssignableFrom(path.getJavaType()) || int.class.isAssignableFrom(path.getJavaType())) {
                                    predicate = cb.and(predicate, (config.getOperation() == GREATER_THAN_OR_EQUAL)
                                            ? cb.greaterThanOrEqualTo(path.as(Integer.class), numValue.intValue())
                                            : cb.lessThanOrEqualTo(path.as(Integer.class), numValue.intValue()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return predicate;
    }

    private Predicate buildRatingPredicate(Predicate predicate, Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Double minRating = (Double) getFieldValue("minRating");
        if(minRating != null) {
            Subquery<Double> ratingSubquery = query.subquery(Double.class);
            Root<Review> reviewRoot = ratingSubquery.from(Review.class);
            ratingSubquery.select(cb.avg(reviewRoot.get("rating")))
                    .where(cb.equal(reviewRoot.get("pet").get("petId"), root.get("petId")), cb.isNotNull(reviewRoot.get("rating")));

            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(ratingSubquery.getSelection(), cb.literal(minRating)));
        }
        return predicate;
    }

    private Predicate buildOnSalePredicate(Predicate predicate, Root<Pet> root, CriteriaBuilder cb) {
        Boolean onSale = (Boolean) getFieldValue("onSale");
        if(onSale != null && onSale) {
            predicate = cb.and(predicate, cb.lessThan(root.get("discountPrice"), root.get("price")));
        }
        return predicate;
    }

    private Object getFieldValue(String fieldName) {
        return switch (fieldName) {
            case "name" -> requestDTO.getName();
            case "categoryId" -> requestDTO.getCategoryId();
            case "minAge" -> requestDTO.getMinAge();
            case "maxAge" -> requestDTO.getMaxAge();
            case "minPrice" -> requestDTO.getMinPrice();
            case "maxPrice" -> requestDTO.getMaxPrice();
            case "healthStatus" -> requestDTO.getHealthStatus();
            case "minWeight" -> requestDTO.getMinWeight();
            case "maxWeight" -> requestDTO.getMaxWeight();
            case "minHeight" -> requestDTO.getMinHeight();
            case "maxHeight" -> requestDTO.getMaxHeight();
            case "minRating" -> requestDTO.getMinRating();
            case "onSale" -> requestDTO.getOnSale();
            case "color" -> requestDTO.getColor();
            case "furType" -> requestDTO.getFurType();
            default -> null;
        };
    }
}
