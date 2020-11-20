package lessonfour.jpa.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificationsBuilder<T> {
    private final List<SearchCriteria> params;

    public SpecificationsBuilder() {
        this.params = new ArrayList<>();
    }

    public SpecificationsBuilder<T> with(String key, String operation, Object value, boolean isOrPredicate) {
        params.add(new SearchCriteria(key, operation, value, isOrPredicate));
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(SpecificationImpl::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(specs.get(i))
                    : Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}

