package com.joaovpsguimaraes.magalums.config;

import com.joaovpsguimaraes.magalums.entity.Channel;
import com.joaovpsguimaraes.magalums.entity.Status;
import com.joaovpsguimaraes.magalums.repository.ChannelRepository;
import com.joaovpsguimaraes.magalums.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public DataLoader(
            ChannelRepository channelRepository,
            StatusRepository statusRepository
    ) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);
    }
}
